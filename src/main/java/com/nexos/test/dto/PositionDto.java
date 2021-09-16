package com.nexos.test.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PositionDto {

	private Long id;

	@NotBlank
	@Size(max = 30)
	private String name;

}
