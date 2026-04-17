package com.satoru.book.service.impl;

import com.satoru.book.model.dto.UserDTO;
import com.satoru.book.model.entity.User;
import com.satoru.book.repository.UserRepository;
import com.satoru.book.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(user -> modelMapper.map(user, UserDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all users: " + e.getMessage());
        }
    }

    @Override
    public UserDTO getUserById(Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
            return modelMapper.map(user, UserDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch user with ID " + userId + ": " + e.getMessage());
        }
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        try {
            User user = modelMapper.map(userDTO, User.class);
            user = userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create user: " + e.getMessage());
        }
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        try {
            User existingUser = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
            modelMapper.map(userDTO, existingUser);
            existingUser.setUserId(userId);
            existingUser = userRepository.save(existingUser);
            return modelMapper.map(existingUser, UserDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user with ID " + userId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(Long userId) {
        try {
            if (!userRepository.existsById(userId)) {
                throw new RuntimeException("User not found with ID: " + userId);
            }
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user with ID " + userId);
        }
    }
}