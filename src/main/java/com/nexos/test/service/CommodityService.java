package com.nexos.test.service;

import java.util.List;

import com.nexos.test.dto.CommodityDto;

public interface CommodityService {

	public abstract boolean isCommodityIsvailableByName(CommodityDto commodityDto);
	
	public abstract List<CommodityDto> getListCommodity(String name, String date, Long creatorUser);

	public abstract CommodityDto getCommodityById(Long id);
	
	public abstract CommodityDto saveCommodity(CommodityDto commodityDto);

	public abstract CommodityDto updateCommodity(Long id, CommodityDto commodityDto);

	public abstract void deleteCommodity(Long id);

}
