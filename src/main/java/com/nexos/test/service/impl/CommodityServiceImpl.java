package com.nexos.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nexos.test.dto.CommodityDto;
import com.nexos.test.entity.Commodity;
import com.nexos.test.repository.CommodityRepository;
import com.nexos.test.repository.criteria.CommodityRepositoryCriteria;
import com.nexos.test.service.CommodityService;

@Service("commodityServiceImpl")
public class CommodityServiceImpl implements CommodityService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	@Qualifier("commodityRepository")
	private CommodityRepository commodityRepository;

	@Autowired
	@Qualifier("commodityRepositoryCriteria")
	private CommodityRepositoryCriteria commodityRepositoryCriteria;

	@Override
	public boolean isCommodityIsvailableByName(CommodityDto commodityDto) {

		String name = commodityDto.getName();
		Optional<Commodity> optCommodity = commodityRepository.getCommodityOrderByName(name);

		if (optCommodity.isPresent()) {
			return false;
		}

		return true;
	}

	@Override
	public List<CommodityDto> getListCommodity(String name, String date, Long creatorUser) {

		List<Commodity> listCommodity = commodityRepositoryCriteria.getListCommodity(name, date, creatorUser);
		List<CommodityDto> listCommodityDto = new ArrayList<CommodityDto>();

		for (Commodity commodity : listCommodity) {

			listCommodityDto.add(entityConvertToDto(commodity));
		}

		return listCommodityDto;
	}

	@Override
	public CommodityDto getCommodityById(Long id) {

		Optional<Commodity> optCommodity = commodityRepository.findById(id);

		if (optCommodity.isEmpty()) {
			return null;
		}

		return entityConvertToDto(optCommodity.get());
	}

	@Override
	public CommodityDto saveCommodity(CommodityDto commodityDto) {

		Commodity commodity = commodityRepository.save(dtoConvertToEntity(null, commodityDto));

		return entityConvertToDto(commodity);
	}

	@Override
	public CommodityDto updateCommodity(Long id, CommodityDto commodityDto) {

		Commodity commodity = commodityRepository.save(dtoConvertToEntity(id, commodityDto));

		return entityConvertToDto(commodity);
	}

	@Override
	public void deleteCommodity(Long id) {

		commodityRepository.deleteById(id);
	}

	private CommodityDto entityConvertToDto(Commodity commodity) {

		return modelMapper.map(commodity, CommodityDto.class);
	}

	private Commodity dtoConvertToEntity(Long id, CommodityDto commodityDto) {

		Commodity commodity = modelMapper.map(commodityDto, Commodity.class);

		if (id != null) {

			Commodity oldCommodity = commodityRepository.findById(id).get();
			commodity.setId(oldCommodity.getId());
			commodity.setCreatorUser(oldCommodity.getCreatorUser());
		}

		return commodity;
	}

}
