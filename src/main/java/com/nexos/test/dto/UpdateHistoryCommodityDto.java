package com.nexos.test.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHistoryCommodityDto {

	private CommodityDto commodity;
	
	private String name;

	private String product;

	private Integer quantity;

	private Date dateOfAdmission;

	private Date creationDate;

	private UserDto creatorUser;

}
