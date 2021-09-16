package com.nexos.test.restController;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexos.test.dto.PositionDto;
import com.nexos.test.exception.ResourceNotFoundException;
import com.nexos.test.response.MessageResponse;
import com.nexos.test.service.PositionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/position")
@Slf4j
public class PositionRestController {

	@Autowired
	@Qualifier("positionServiceImpl")
	private PositionService positionService;

	@GetMapping("/list")
	public ResponseEntity<List<PositionDto>> getPositions() {

		log.info("RECEIVED PARAMETERS: []");

		List<PositionDto> positionsDto = positionService.getPositions();

		log.info("RESPONSE DATA: '" + positionsDto + "'");

		return ResponseEntity.ok(positionsDto);
	}

	@GetMapping("/by-id/{id}")
	public ResponseEntity<PositionDto> getPositionById(@PathVariable("id") Long id) {

		log.info("RECEIVED PARAMETERS: 'id: " + id + "'");

		PositionDto positionDto = positionService.getPositionById(id);

		log.info("RESPONSE DATA: '" + positionDto + "'");

		return ResponseEntity.ok(positionDto);
	}

	@Transactional
	@PostMapping("/save")
	public ResponseEntity<MessageResponse> savePosition(@RequestBody @Valid PositionDto positionDto,
			BindingResult result) {

		log.info("RECEIVED PARAMETERS: '" + positionDto + "'");

		MessageResponse message = new MessageResponse();

		positionService.savePosition(positionDto);
		message = new MessageResponse(false, "Cargo guardado");

		log.info("RESPONSE DATA: '" + message + "'");

		return ResponseEntity.ok(message);
	}

	@Transactional
	@PutMapping("/update/{id}")
	public ResponseEntity<MessageResponse> updatePosition(@PathVariable("id") Long id,
			@RequestBody @Valid PositionDto positionDto, BindingResult result) throws ResourceNotFoundException {

		log.info("RECEIVED PARAMETERS: 'id: " + id + "', " + positionDto + "'");

		Optional.ofNullable(positionService.getPositionById(id))
				.orElseThrow(() -> new ResourceNotFoundException("Cargo no encontrado para este id :: " + id));

		positionService.updatePosition(positionDto);
		MessageResponse message = new MessageResponse(false, "Cargo actualizado");

		log.info("RESPONSE DATA: '" + message + "'");

		return ResponseEntity.ok(message);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<MessageResponse> deletePosition(@PathVariable("id") Long id)
			throws ResourceNotFoundException {

		log.info("RECEIVED PARAMETERS: 'id: " + id + "'");

		Optional.ofNullable(positionService.getPositionById(id))
				.orElseThrow(() -> new ResourceNotFoundException("Cargo no encontrado para este id :: " + id));

		positionService.deletePosition(id);
		MessageResponse message = new MessageResponse(false, "Cargo eliminado");

		log.info("RESPONSE DATA: '" + message + "'");

		return ResponseEntity.ok(message);
	}

}
