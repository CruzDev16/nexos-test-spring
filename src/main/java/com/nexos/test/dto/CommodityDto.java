package com.nexos.test.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommodityDto {

	private Long id;

	@NotBlank
	@Size(max = 30)
	private String name;

	@NotBlank
	@Size(max = 30)
	private String product;

	@NotNull
	private Integer quantity;

	@NotNull
	private Date dateOfAdmission;

	@NotNull
	private UserDto creatorUser;

}
