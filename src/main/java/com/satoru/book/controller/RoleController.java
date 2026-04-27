package com.satoru.book.controller;

import com.satoru.book.model.dto.RoleDTO;
import com.satoru.book.model.dto.responseDto.RoleRespDTO;
import com.satoru.book.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleRespDTO>> getAllRoles() {
        List<RoleRespDTO> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleRespDTO> getRoleById(@PathVariable("id") Long roleId) {
        RoleRespDTO roleDTO = roleService.getRoleById(roleId);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO roleDTO) {
        RoleDTO createdRoleDTO = roleService.createRole(roleDTO);
        return new ResponseEntity<>(createdRoleDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable("id") Long roleId, @Valid @RequestBody RoleDTO roleDTO) {
        RoleDTO updatedRoleDTO = roleService.updateRole(roleId, roleDTO);
        return new ResponseEntity<>(updatedRoleDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Long roleId) {
        roleService.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

