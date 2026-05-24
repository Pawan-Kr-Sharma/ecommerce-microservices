package com.pk.ecommerce.user.service;

import com.pk.ecommerce.user.dto.LoginRequestDto;
import com.pk.ecommerce.user.dto.LoginResponseDto;
import com.pk.ecommerce.user.dto.UserCreateRequestDto;
import com.pk.ecommerce.user.dto.UserResponseDto;

public interface UserService {

	UserResponseDto register(UserCreateRequestDto dto);
	
	LoginResponseDto login(LoginRequestDto dto);
	
	UserResponseDto getById(Long id);
}
