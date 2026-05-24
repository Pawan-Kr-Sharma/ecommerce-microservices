package com.pk.ecommerce.user.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

	private Long id;
	private String name;
	private String email;
	private String phone;
	private String roles;
	private Instant createdAt;
	private Instant updatedAt;
}
