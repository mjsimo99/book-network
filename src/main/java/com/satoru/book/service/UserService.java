package com.satoru.book.service;

import com.satoru.book.model.dto.UserDTO;
import com.satoru.book.model.dto.responseDto.UserRespDTO;

import java.util.List;

public interface UserService {
    List<UserRespDTO> getAllUsers();
    UserRespDTO getUserById(Long userId);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long userId, UserDTO userDTO);
    void deleteUser(Long userId);
}