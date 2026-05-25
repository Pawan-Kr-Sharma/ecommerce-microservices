package com.pk.ecommerce.user.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pk.ecommerce.user.dto.LoginRequestDto;
import com.pk.ecommerce.user.dto.LoginResponseDto;
import com.pk.ecommerce.user.dto.UserCreateRequestDto;
import com.pk.ecommerce.user.dto.UserResponseDto;
import com.pk.ecommerce.user.entity.User;
import com.pk.ecommerce.user.exception.ResourceAlreadyExistsException;
import com.pk.ecommerce.user.exception.ResourceNotFoundException;
import com.pk.ecommerce.user.mapper.AuthMapper;
import com.pk.ecommerce.user.mapper.UserMapper;
import com.pk.ecommerce.user.repository.UserRepository;
import com.pk.ecommerce.user.service.UserService;
import com.pk.ecommerce.user.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	private final UserMapper userMapper;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtUtil jwtUtil;
	
	private final AuthMapper authMapper;
	
	@Override
	public UserResponseDto register(UserCreateRequestDto dto) {
		log.info("Register user !!");
		
		if(userRepository.existByEmail(dto.getEmail())) {
			log.warn("User already exist with given email: {}",dto.getEmail());
			throw new ResourceAlreadyExistsException("Email Already Registered");
		}
		
		//classical way
		/*User user = User.builder()
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
				.build();*/
		
		User user = userRepository.save(userMapper.toEntity(dto));
		return userMapper.toDto(user);
	}

	@Override
	public LoginResponseDto login(LoginRequestDto dto) {
		
		User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Invalid credentials"));
		
		boolean matches = passwordEncoder.matches(dto.getPassword(), user.getPassword());
		
		if(!matches) {
			throw new ResourceNotFoundException("Invalid credentials !!");
		}
		
		String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRoles());
		
		return authMapper.toLoginResponse(user, token);
	}

	@Override
	public UserResponseDto getById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with given Id: " + id));
		return userMapper.toDto(user);
	}

}
