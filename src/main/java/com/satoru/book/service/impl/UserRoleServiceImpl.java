package com.satoru.book.service.impl;

import com.satoru.book.model.dto.UserRoleDTO;
import com.satoru.book.model.dto.responseDto.UserRoleRespDTO;
import com.satoru.book.model.entity.Role;
import com.satoru.book.model.entity.User;
import com.satoru.book.model.entity.UserRole;
import com.satoru.book.model.identity.UserRoleId;
import com.satoru.book.repository.RoleRepository;
import com.satoru.book.repository.UserRepository;
import com.satoru.book.repository.UserRoleRepository;
import com.satoru.book.service.UserRoleService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserRoleRespDTO> getAllUserRoles() {
        try {
            List<UserRole> userRoles = userRoleRepository.findAll();
            return userRoles.stream()
                    .map(userRole -> modelMapper.map(userRole, UserRoleRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all user roles: " + e.getMessage());
        }
    }

    @Override
    public UserRoleRespDTO getUserRoleById(Long userId, Long roleId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new NotFoundException("Role not found with ID: " + roleId));
            UserRoleId id = new UserRoleId(user, role);
            UserRole userRole = userRoleRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User role not found for user ID: " + userId + " and role ID: " + roleId));
            return modelMapper.map(userRole, UserRoleRespDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch user role for user ID " + userId + " and role ID " + roleId + ": " + e.getMessage());
        }
    }

    @Override
    public UserRoleDTO createUserRole(UserRoleDTO userRoleDTO) {
        try {
            Long userId = userRoleDTO.getUserId();
            Long roleId = userRoleDTO.getRoleId();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleId));
            UserRoleId id = new UserRoleId(user, role);
            UserRole userRole = new UserRole();
            userRole.setId(id);
            userRole = userRoleRepository.save(userRole);
            return modelMapper.map(userRole, UserRoleDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create user role: " + e.getMessage());
        }
    }

    @Override
    public UserRoleDTO updateUserRole(Long userId, Long roleId, UserRoleDTO userRoleDTO) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new NotFoundException("Role not found with ID: " + roleId));
            UserRoleId id = new UserRoleId(user, role);
            UserRole existingUserRole = userRoleRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User role not found for user ID: " + userId + " and role ID: " + roleId));

            return modelMapper.map(existingUserRole, UserRoleDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user role for user ID " + userId + " and role ID " + roleId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteUserRole(Long userId, Long roleId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new NotFoundException("Role not found with ID: " + roleId));
            UserRoleId id = new UserRoleId(user, role);
            if (!userRoleRepository.existsById(id)) {
                throw new NotFoundException("User role not found for user ID: " + userId + " and role ID: " + roleId);
            }
            userRoleRepository.deleteById(id);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user role for user ID " + userId + " and role ID " + roleId);
        }
    }
}