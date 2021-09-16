package com.nexos.test.service;

import java.util.List;

import com.nexos.test.dto.PositionDto;

public interface PositionService {

	public abstract List<PositionDto> getPositions();
	
	public abstract PositionDto getPositionById(Long id);

	public abstract PositionDto savePosition(PositionDto positionDto);

	public abstract PositionDto updatePosition(PositionDto positionDto);

	public abstract void deletePosition(Long id);

}
