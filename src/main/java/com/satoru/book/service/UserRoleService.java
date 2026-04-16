package com.satoru.book.service;

import com.satoru.book.model.dto.UserRoleDTO;

import java.util.List;

public interface UserRoleService {
    List<UserRoleDTO> getAllUserRoles();
    UserRoleDTO getUserRoleById(Long userId, Long roleId);
    UserRoleDTO createUserRole(UserRoleDTO userRoleDTO);
    UserRoleDTO updateUserRole(Long userId, Long roleId, UserRoleDTO userRoleDTO);
    void deleteUserRole(Long userId, Long roleId);
}