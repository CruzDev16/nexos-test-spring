package com.nexos.test.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexos.test.dto.UserDto;
import com.nexos.test.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserRestController {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@GetMapping("/list")
	public List<UserDto> getUsers() {

		log.info("RECEIVED PARAMETERS: []");

		List<UserDto> usersDto = userService.getUsers();

		log.info("RESPONSE DATA: '" + usersDto + "'");

		return usersDto;
	}

}
