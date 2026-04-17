package com.satoru.book.service.impl;

import com.satoru.book.model.dto.RoleDTO;
import com.satoru.book.model.dto.responseDto.RoleRespDTO;
import com.satoru.book.model.entity.Role;
import com.satoru.book.repository.RoleRepository;
import com.satoru.book.service.RoleService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RoleRespDTO> getAllRoles() {
        try {
            List<Role> roles = roleRepository.findAll();
            return roles.stream()
                    .map(role -> modelMapper.map(role, RoleRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all roles: " + e.getMessage());
        }
    }

    @Override
    public RoleRespDTO getRoleById(Long roleId) {
        try {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new NotFoundException("Role not found with ID: " + roleId));
            return modelMapper.map(role, RoleRespDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch role with ID " + roleId + ": " + e.getMessage());
        }
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        try {
            Role role = modelMapper.map(roleDTO, Role.class);
            role = roleRepository.save(role);
            return modelMapper.map(role, RoleDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create role: " + e.getMessage());
        }
    }

    @Override
    public RoleDTO updateRole(Long roleId, RoleDTO roleDTO) {
        try {
            Role existingRole = roleRepository.findById(roleId)
                    .orElseThrow(() -> new NotFoundException("Role not found with ID: " + roleId));
            modelMapper.map(roleDTO, existingRole);
            existingRole.setRoleId(roleId);
            existingRole = roleRepository.save(existingRole);
            return modelMapper.map(existingRole, RoleDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update role with ID " + roleId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteRole(Long roleId) {
        try {
            if (!roleRepository.existsById(roleId)) {
                throw new NotFoundException("Role not found with ID: " + roleId);
            }
            roleRepository.deleteById(roleId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete role with ID " + roleId);
        }
    }
}