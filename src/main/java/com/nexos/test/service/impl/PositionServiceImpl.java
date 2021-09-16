package com.nexos.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nexos.test.dto.PositionDto;
import com.nexos.test.entity.Position;
import com.nexos.test.repository.PositionRepository;
import com.nexos.test.service.PositionService;

@Service("positionServiceImpl")
public class PositionServiceImpl implements PositionService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	@Qualifier("positionRepository")
	private PositionRepository positionRepository;

	@Override
	public List<PositionDto> getPositions() {

		List<Position> positions = positionRepository.getPositions();
		List<PositionDto> positionsDto = new ArrayList<PositionDto>();

		for (Position position : positions) {

			positionsDto.add(entityConvertToDto(position));
		}

		return positionsDto;
	}

	@Override
	public PositionDto getPositionById(Long id) {

		Optional<Position> optPosition = positionRepository.findById(id);

		if (optPosition.isEmpty()) {
			return null;
		}

		return entityConvertToDto(optPosition.get());
	}

	@Override
	public PositionDto savePosition(PositionDto positionDto) {

		Position position = positionRepository.save(dtoConvertToEntity(positionDto));

		return entityConvertToDto(position);
	}

	@Override
	public PositionDto updatePosition(PositionDto positionDto) {

		Position position = positionRepository.save(dtoConvertToEntity(positionDto));

		return entityConvertToDto(position);
	}

	@Override
	public void deletePosition(Long id) {

		positionRepository.deleteById(id);
	}

	private PositionDto entityConvertToDto(Position position) {

		return modelMapper.map(position, PositionDto.class);
	}

	private Position dtoConvertToEntity(PositionDto positionDto) {

		return modelMapper.map(positionDto, Position.class);
	}

}
