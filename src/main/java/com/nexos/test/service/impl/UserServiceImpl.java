package com.nexos.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nexos.test.dto.UserDto;
import com.nexos.test.entity.User;
import com.nexos.test.repository.UserRepository;
import com.nexos.test.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Override
	public List<UserDto> getUsers() {

		List<User> users = userRepository.getUsers();
		List<UserDto> usersDto = new ArrayList<UserDto>();

		for (User user : users) {

			usersDto.add(entityConvertToDto(user));
		}

		return usersDto;
	}

	@Override
	public UserDto getUserById(Long id) {

		Optional<User> optUser = userRepository.findById(id);

		if (optUser.isEmpty()) {
			return null;
		}

		return entityConvertToDto(optUser.get());
	}

	private UserDto entityConvertToDto(User user) {

		return modelMapper.map(user, UserDto.class);
	}

}
