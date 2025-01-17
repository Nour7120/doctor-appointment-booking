package com.practice.doctor_appointment_booking.core.internal.services;

import com.practice.doctor_appointment_booking.core.CustomExceptionDTO;
import com.practice.doctor_appointment_booking.core.ExposedServices;
import com.practice.doctor_appointment_booking.core.internal.repositories.ClientRepository;
import com.practice.doctor_appointment_booking.core.internal.repositories.GroupRepository;
import com.practice.doctor_appointment_booking.core.internal.daos.Client;
import com.practice.doctor_appointment_booking.core.internal.daos.Group;
import com.practice.doctor_appointment_booking.core.internal.daos.GroupEnum;
import com.practice.doctor_appointment_booking.core.internal.dtos.GetClientDTO;
import com.practice.doctor_appointment_booking.core.internal.dtos.GetGroupDTO;
import com.practice.doctor_appointment_booking.core.internal.dtos.GetGroupWithClientsDTO;
import com.practice.doctor_appointment_booking.core.internal.exceptions.CustomException;
import com.practice.doctor_appointment_booking.core.internal.exceptions.ExceptionMessages;
import com.practice.doctor_appointment_booking.core.internal.requests.*;
import com.practice.doctor_appointment_booking.core.internal.responses.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthorizationService implements ExposedServices {

    private final ClientRepository clientRepository;
    private final GroupRepository groupRepository;

    @Transactional
    public AddGroupResponse createGroup(AddGroupRequest addGroupRequest) throws CustomException {
        log.info("Started");
        GroupEnum groupEnum;
        try {
            groupEnum = GroupEnum.valueOf(addGroupRequest.getName());
        } catch (IllegalArgumentException e) {
            throw new CustomException(ExceptionMessages.GROUP_TYPE_NOT_AVAILABLE.getErrorMessage());
        }
        List<Group> groups = groupRepository.findByNameAndDeletedIsFalse(groupEnum);
        if (!groups.isEmpty())
            throw new CustomException(ExceptionMessages.GROUP_ALREADY_EXISTS.getErrorMessage());
        Group savedGroup = groupRepository.save(Group.builder().name(groupEnum).build());
        log.info("Finished");
        return AddGroupResponse.builder().id(savedGroup.getId()).statusCode(HttpStatus.CREATED.value()).build();
    }

    @Transactional
    public AssignClientsToGroupResponse assignClientsToGroup(AssignClientsToGroupRequest assignClientsToGroupRequest) throws CustomException {
        log.info("Started");
        long groupID = assignClientsToGroupRequest.getGroupId();
        log.info("Group Id: {}", groupID); //TODO: Remove this.
        Group group = groupRepository.findByIdAndDeletedIsFalse(groupID).orElseThrow(
                () -> new CustomException(ExceptionMessages.GROUP_NOT_FOUND.getErrorMessage())
        );
        Set<Client> clientList = clientRepository.findDistinctByIdIsIn(assignClientsToGroupRequest.getClients());
        log.info("Client List: {}", clientList); //TODO: Remove this.
        clientList.forEach(client -> client.getGroups().add(group));
        group.setClients(clientList);
        log.info("Finished");
        return AssignClientsToGroupResponse.builder().statusCode(HttpStatus.OK.value()).build();
    }

    @Transactional
    public DeleteClientsFromGroupResponse deleteClientsFromGroup(DeleteClientsFromGroupRequest deleteClientsFromGroupRequest) throws CustomException {
        log.info("Started");
        Group group = groupRepository.findByIdAndDeletedIsFalse(deleteClientsFromGroupRequest.getGroupId()).orElseThrow(
                () -> new CustomException(ExceptionMessages.GROUP_NOT_FOUND.getErrorMessage())
        );
        Set<Client> clientList = clientRepository.findDistinctByIdIsIn(deleteClientsFromGroupRequest.getClients());
        clientList.forEach(client -> client.getGroups().remove(group));
        group.getClients().removeAll(clientList);
        log.info("Finished");
        return DeleteClientsFromGroupResponse.builder().statusCode(HttpStatus.OK.value()).build();
    }

    @Transactional
    public DeleteGroupResponse deleteGroup(long groupId) throws CustomException {
        log.info("Started");
        Group group = groupRepository.findByIdAndDeletedIsFalse(groupId).orElseThrow(
                () -> new CustomException(ExceptionMessages.GROUP_NOT_FOUND.getErrorMessage())
        );
        group.setDeleted(true);
        log.info("Finished");
        return DeleteGroupResponse.builder().statusCode(HttpStatus.OK.value()).build();
    }

    @Transactional
    public UpdateGroupResponse updateGroup(UpdateGroupRequest updateGroupRequest) throws CustomException {
        log.info("Started");
        Group group = groupRepository.findByIdAndDeletedIsFalse(updateGroupRequest.getId()).orElseThrow(
                () -> new CustomException(ExceptionMessages.GROUP_NOT_FOUND.getErrorMessage())
        );
        group.setName(GroupEnum.valueOf(updateGroupRequest.getName()));
        log.info("Finished");
        return UpdateGroupResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .id(group.getId())
                .name(group.getName().name())
                .build();
    }

    public GetGroupsByClientIdResponse getGroupsByClientId(long clientId) throws CustomException {
        log.info("Started");
        Client client = clientRepository.findByIdAndDeletedIsFalse(clientId).orElseThrow(
                () -> new CustomException(ExceptionMessages.CLIENT_NOT_FOUND.getErrorMessage())
        );
        log.debug("Client username: {}", client.getUsername());
        Set<Group> groupSet = client.getGroups();
        List<GetGroupDTO> groups = groupSet.stream()
                .map(
                        group -> {
                            if (!group.isDeleted())
                                return GetGroupDTO.builder().id(group.getId()).name(group.getName().name()).build();
                            return null;
                        }
                )
                .toList();
        log.info("Finished");
        return GetGroupsByClientIdResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .groupsCount(groups.size())
                .groups(groups)
                .build();
    }

    public GetGroupsResponse getGroups() {
        log.info("Started");
        List<Group> groupList = groupRepository.findAllByDeletedIsFalse();
        log.debug("Group List: {}", groupList);
        List<GetGroupWithClientsDTO> groups = groupList.stream()
                .map(group -> {
                    GroupEnum groupNameEnum = group.getName();
                    List<GetClientDTO> clients = group.getClients()
                            .stream()
                            .map(client -> GetClientDTO.builder()
                                    .id(client.getId())
                                    .username(client.getUsername()).build())
                            .toList();
                    return GetGroupWithClientsDTO.builder().id(group.getId()).name(groupNameEnum.name()).clients(clients).build();
                })
                .toList();
        log.info("Finished");
        return GetGroupsResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .groupsCount(groupList.size())
                .groups(groups)
                .build();
    }

    @Transactional
    public AddClientResponse createClient(AddClientRequest addClientRequest) throws CustomException {
        log.info("Started");
        String clientUsername = addClientRequest.getUsername();
        List<Client> clientList = clientRepository.findByUsernameAndDeletedIsFalse(clientUsername);
        if (!clientList.isEmpty())
            throw new CustomException(ExceptionMessages.CLIENT_ALREADY_EXISTS.getErrorMessage());
        Client savedClient = clientRepository.save(Client.builder().username(clientUsername).build());
        log.info("Finished");
        return AddClientResponse.builder().id(savedClient.getId()).statusCode(HttpStatus.CREATED.value()).build();
    }

    @Override
    public String getDoctorUsername(long doctorId) throws CustomExceptionDTO {
        Client client = clientRepository.findById(doctorId).orElseThrow(() -> new CustomExceptionDTO(ExceptionMessages.CLIENT_NOT_FOUND.getErrorMessage()));
        return client.getUsername();
    }
}
