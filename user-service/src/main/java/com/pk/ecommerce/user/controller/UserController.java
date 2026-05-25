package com.pk.ecommerce.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk.ecommerce.user.dto.LoginRequestDto;
import com.pk.ecommerce.user.dto.LoginResponseDto;
import com.pk.ecommerce.user.dto.UserCreateRequestDto;
import com.pk.ecommerce.user.dto.UserResponseDto;
import com.pk.ecommerce.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserCreateRequestDto dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(dto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){
		return ResponseEntity.ok(userService.login(dto));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id/* , Authentication authentication */){
		//Authentication use to check is id login user id.
		//Authentication object in Spring Security contains details of the currently logged-in user.
		//We use it for authorization checks, extracting username/roles, and implementing user-specific access control.
		
		/*
		String loggedInEmail = authentication.getName();
	    UserResponseDto user = userService.getById(id);

	    // Example authorization check
	    if (!user.getEmail().equals(loggedInEmail)) {
	        throw new RuntimeException("Access Denied");
	    }*/
		return ResponseEntity.ok(userService.getById(id));
	}
}
