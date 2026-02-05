package com.vriur.user_service.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.vriur.user_service.dto.UserRequestDto;
import com.vriur.user_service.dto.UserResponseDto;

public interface IUserService {
    public Page<UserResponseDto> getAllUsers(int page, int pageSize);

    public UserResponseDto getUserById(UUID id);

    public UserResponseDto createUser(UserRequestDto userRequestDto);

    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto);

    public void deleteUser(UUID id);
}
