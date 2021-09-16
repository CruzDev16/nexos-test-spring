package com.nexos.test.service;

import java.util.List;

import com.nexos.test.dto.UserDto;

public interface UserService {

	public abstract List<UserDto> getUsers();

	public abstract UserDto getUserById(Long id);

}
