package com.pk.ecommerce.user.service.impl;

import org.springframework.stereotype.Service;

import com.pk.ecommerce.user.dto.LoginRequestDto;
import com.pk.ecommerce.user.dto.LoginResponseDto;
import com.pk.ecommerce.user.dto.UserCreateRequestDto;
import com.pk.ecommerce.user.dto.UserResponseDto;
import com.pk.ecommerce.user.entity.User;
import com.pk.ecommerce.user.repository.UserRepository;
import com.pk.ecommerce.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Override
	public UserResponseDto register(UserCreateRequestDto dto) {
		log.info("Register user !!");
		
		if(userRepository.existByEmail(dto.getEmail())) {
			log.warn("User already exist with given email: {}",dto.getEmail());
			throw new RuntimeException("Already exist !!");
		}
		
		User user = User.builder()
		.name(dto.getName())
		.email(dto.getEmail())
		.password(dto.getPassword())
		.phone(dto.getPhone()).build();
		
		User savedUser = userRepository.save(user);
		
		log.info("User saved successfully with id: {}", savedUser.getId());
		return UserResponseDto.builder()
				.name(savedUser.getName())
				.email(savedUser.getEmail())
				.phone(savedUser.getPhone())
				.roles(savedUser.getRoles())
				.createdAt(savedUser.getCreatedAt())
				.updatedAt(savedUser.getUpdatedAt())
				.build();
	}

	@Override
	public LoginResponseDto login(LoginRequestDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDto getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
