package com.nexos.test.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nexos.test.entity.Position;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

	private Long id;

	@NotBlank
	@Size(max = 30)
	private String name;

	@NotNull
	private Integer age;

	@NotNull
	private Position position;

	@NotNull
	private Date dateOfAdmission;

}
