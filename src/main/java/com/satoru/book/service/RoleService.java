package com.satoru.book.service;

import com.satoru.book.model.dto.RoleDTO;
import com.satoru.book.model.dto.responseDto.RoleRespDTO;

import java.util.List;

public interface RoleService {
    List<RoleRespDTO> getAllRoles();
    RoleRespDTO getRoleById(Long roleId);
    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO updateRole(Long roleId, RoleDTO roleDTO);
    void deleteRole(Long roleId);
}