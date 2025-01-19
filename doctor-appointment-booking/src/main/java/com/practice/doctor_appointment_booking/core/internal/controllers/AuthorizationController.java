package com.practice.doctor_appointment_booking.core.internal.controllers;

import com.practice.doctor_appointment_booking.core.internal.services.AuthorizationService;
import com.practice.doctor_appointment_booking.core.internal.exceptions.CustomException;
import com.practice.doctor_appointment_booking.core.internal.requests.*;
import com.practice.doctor_appointment_booking.core.internal.responses.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/authorization")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/groups")
    public ResponseEntity<AddGroupResponse> createGroup(@RequestBody AddGroupRequest addGroupRequest) throws CustomException {
        log.info("Started");
        AddGroupResponse addGroupResponse = authorizationService.createGroup(addGroupRequest);
        log.info("Finished");
        return ResponseEntity.status(HttpStatus.CREATED).body(addGroupResponse);
    }

    @PostMapping("/clients")
    public ResponseEntity<AddClientResponse> createClient(@RequestBody AddClientRequest addClientRequest) throws CustomException {
        log.info("Started");
        AddClientResponse addClientResponse = authorizationService.createClient(addClientRequest);
        log.info("Finished");
        return ResponseEntity.status(HttpStatus.CREATED).body(addClientResponse);
    }

    @PostMapping("/assignations")
    public ResponseEntity<AssignClientsToGroupResponse> assignClientsToGroup(@RequestBody AssignClientsToGroupRequest assignClientsToGroupRequest) throws CustomException {
        log.info("Started");
        AssignClientsToGroupResponse assignClientsToGroupResponse = authorizationService.assignClientsToGroup(assignClientsToGroupRequest);
        log.info("Finished");
        return ResponseEntity.status(HttpStatus.OK).body(assignClientsToGroupResponse);
    }

    @GetMapping("/groups")
    public ResponseEntity<GetGroupsResponse> getGroups()
    {
        log.info("Started");
        GetGroupsResponse getGroupsResponse = authorizationService.getGroups();
        log.info("Finished");
        return ResponseEntity.status(HttpStatus.OK).body(getGroupsResponse);
    }

    @GetMapping("/groups/{clientId}")
    public ResponseEntity<GetGroupsByClientIdResponse> getGroupsByClientId(@PathVariable long clientId) throws CustomException {
        log.info("Started");
        GetGroupsByClientIdResponse getGroupsResponse = authorizationService.getGroupsByClientId(clientId);
        log.info("Finished");
        return ResponseEntity.status(HttpStatus.OK).body(getGroupsResponse);
    }

    @PutMapping("/groups")
    public ResponseEntity<UpdateGroupResponse> updateGroup(@RequestBody UpdateGroupRequest updateGroupRequest) throws CustomException {
        log.info("Started");
        UpdateGroupResponse updateGroupResponse = authorizationService.updateGroup(updateGroupRequest);
        log.info("Finished");
        return ResponseEntity.status(HttpStatus.OK).body(updateGroupResponse);
    }

    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<DeleteGroupResponse> deleteGroup(@PathVariable long groupId) throws CustomException {
        log.info("Started");
        DeleteGroupResponse deleteGroupResponse = authorizationService.deleteGroup(groupId);
        log.info("Finished");
        return ResponseEntity.status(HttpStatus.OK).body(deleteGroupResponse);
    }

    @DeleteMapping("/groups")
    public ResponseEntity<DeleteClientsFromGroupResponse> deleteClientsFromGroup(@RequestBody DeleteClientsFromGroupRequest deleteClientsFromGroupRequest) throws CustomException {
        log.info("Started");
        DeleteClientsFromGroupResponse deleteClientsFromGroupResponse = authorizationService.deleteClientsFromGroup(deleteClientsFromGroupRequest);
        log.info("Finished");
        return ResponseEntity.status(HttpStatus.OK).body(deleteClientsFromGroupResponse);
    }


}
