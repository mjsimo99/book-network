package com.satoru.book.controller;

import com.satoru.book.model.dto.UserRoleDTO;
import com.satoru.book.model.dto.responseDto.UserRoleRespDTO;
import com.satoru.book.service.UserRoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@CrossOrigin
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<List<UserRoleRespDTO>> getAllUserRoles() {
        List<UserRoleRespDTO> userRoles = userRoleService.getAllUserRoles();
        return new ResponseEntity<>(userRoles, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{roleId}")
    public ResponseEntity<UserRoleRespDTO> getUserRoleById(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
        UserRoleRespDTO userRoleDTO = userRoleService.getUserRoleById(userId, roleId);
        return new ResponseEntity<>(userRoleDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserRoleDTO> createUserRole(@Valid @RequestBody UserRoleDTO userRoleDTO) {
        UserRoleDTO createdUserRoleDTO = userRoleService.createUserRole(userRoleDTO);
        return new ResponseEntity<>(createdUserRoleDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/{roleId}")
    public ResponseEntity<UserRoleDTO> updateUserRole(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId, @Valid @RequestBody UserRoleDTO userRoleDTO) {
        UserRoleDTO updatedUserRoleDTO = userRoleService.updateUserRole(userId, roleId, userRoleDTO);
        return new ResponseEntity<>(updatedUserRoleDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{roleId}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
        userRoleService.deleteUserRole(userId, roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

