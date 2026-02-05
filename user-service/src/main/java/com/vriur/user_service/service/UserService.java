package com.vriur.user_service.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vriur.user_service.dto.UserRequestDto;
import com.vriur.user_service.dto.UserResponseDto;
import com.vriur.user_service.mapper.UserMapper;
import com.vriur.user_service.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<UserResponseDto> getAllUsers(int page, int pageSize) {
        // Define the pagination parameters. In a future add sorting options.
        Pageable pageable = PageRequest.of(page, pageSize);

        // Return all users with pagination after been mapped to a DTO.
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public UserResponseDto getUserById(UUID id) {
        // Validate input. Throw exception if invalid.
        Objects.requireNonNull(id, "Id can't be null.");

        // Return user by id after been mapped to a DTO. Throw exception if not found.
        return userRepository.findById(id)
                            .map(userMapper::toDto)
                            .orElseThrow(() -> new EntityNotFoundException("The user with the id: " + id + " was not found."));
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        // Map the DTO to the entity.
        var user = userMapper.toEntity(userRequestDto);

        // Validate input. Throw exception if invalid.
        Objects.requireNonNull(user, "UserRequestDto can't be null.");

        // Save the user into the database.
        var createdUser = userRepository.save(user);

        // Return the created user after been mapped to a DTO.
        return userMapper.toDto(createdUser);
    }

    @Override
    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) {
        // Validate input. Throw exception if invalid.
        Objects.requireNonNull(id, "Id can't be null.");

        // Validate input. Throw exception if invalid.
        Objects.requireNonNull(userRequestDto, "UserRequestDto can't be null.");

        // Ensure the user exists before updating. Throw exception if not found.
        var existingUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The user with the id: " + id + " was not found."));

        // Update the fields of the existing user.
        userMapper.updateEntityFromDto(userRequestDto, existingUser);

        // Save the user into the database.user.
        var updatedUser = userRepository.save(existingUser);

        // Return the updated user after been mapped to a DTO.
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(UUID id) {
        // Validate input. Throw exception if invalid.
        Objects.requireNonNull(id, "Id can't be null.");

        // Ensure the user exists before updating. Throw exception if not found.
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("The user with the id: " + id + " was not found.");
        }

        // Delete the user by id.
        userRepository.deleteById(id);
    }
}
