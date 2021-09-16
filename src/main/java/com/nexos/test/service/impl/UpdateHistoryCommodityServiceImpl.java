package com.nexos.test.service.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nexos.test.dto.CommodityDto;
import com.nexos.test.dto.UpdateHistoryCommodityDto;
import com.nexos.test.dto.UserDto;
import com.nexos.test.entity.UpdateHistoryCommodity;
import com.nexos.test.repository.UpdateHistoryCommodityRepository;
import com.nexos.test.service.UpdateHistoryCommodityService;

@Service("updateHistoryCommodityServiceImpl")
public class UpdateHistoryCommodityServiceImpl implements UpdateHistoryCommodityService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	@Qualifier("updateHistoryCommodityRepository")
	private UpdateHistoryCommodityRepository updateHistoryCommodityRepository;

	@Override
	public UpdateHistoryCommodityDto saveUpdateHistoryCommodity(CommodityDto commodityDto) {

		String name = commodityDto.getName();
		String product = commodityDto.getProduct();
		Integer quantity = commodityDto.getQuantity();
		Date dateOfAdmission = commodityDto.getDateOfAdmission();
		UserDto creatorUser = commodityDto.getCreatorUser();

		UpdateHistoryCommodityDto updateHistoryCommodityDto = new UpdateHistoryCommodityDto(commodityDto, name, product,
				quantity, dateOfAdmission, dateOfAdmission, creatorUser);

		UpdateHistoryCommodity updateHistoryCommodity = updateHistoryCommodityRepository
				.save(dtoConvertToEntity(updateHistoryCommodityDto));

		return entityConvertToDto(updateHistoryCommodity);
	}

	private UpdateHistoryCommodityDto entityConvertToDto(UpdateHistoryCommodity updateHistoryCommodity) {

		return modelMapper.map(updateHistoryCommodity, UpdateHistoryCommodityDto.class);
	}

	private UpdateHistoryCommodity dtoConvertToEntity(UpdateHistoryCommodityDto updateHistoryCommodityDto) {

		return modelMapper.map(updateHistoryCommodityDto, UpdateHistoryCommodity.class);
	}

}
