package com.satoru.book.service;

import com.satoru.book.model.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleById(Long roleId);
    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO updateRole(Long roleId, RoleDTO roleDTO);
    void deleteRole(Long roleId);
}