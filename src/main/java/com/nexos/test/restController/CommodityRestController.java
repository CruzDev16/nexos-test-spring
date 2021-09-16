package com.nexos.test.restController;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexos.test.dto.CommodityDto;
import com.nexos.test.dto.UserDto;
import com.nexos.test.exception.ResourceNotFoundException;
import com.nexos.test.response.MessageResponse;
import com.nexos.test.service.CommodityService;
import com.nexos.test.service.UpdateHistoryCommodityService;
import com.nexos.test.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/commodity")
@Slf4j
public class CommodityRestController {

	@Autowired
	@Qualifier("commodityServiceImpl")
	private CommodityService commodityService;

	@Autowired
	@Qualifier("updateHistoryCommodityServiceImpl")
	private UpdateHistoryCommodityService updateHistoryCommodityService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@GetMapping("/list")
	public ResponseEntity<List<CommodityDto>> getListCommodity(
			@RequestParam(required = false, value = "name") String name,
			@RequestParam(required = false, value = "date") String date,
			@RequestParam(required = false, value = "creatorUser") Long creatorUser) {

		log.info("RECEIVED PARAMETERS: 'name: " + name + "', 'date: " + date + "', 'creatorUser: " + creatorUser + "'");

		List<CommodityDto> listCommodityDto = commodityService.getListCommodity(name, date, creatorUser);

		log.info("RESPONSE DATA: '" + listCommodityDto + "'");

		return ResponseEntity.ok(listCommodityDto);
	}

	@GetMapping("/by-id/{id}")
	public ResponseEntity<CommodityDto> getCommodityById(@PathVariable("id") Long id) {

		log.info("RECEIVED PARAMETERS: 'id: " + id + "'");

		CommodityDto commodityDto = commodityService.getCommodityById(id);

		log.info("RESPONSE DATA: '" + commodityDto + "'");

		return ResponseEntity.ok(commodityDto);
	}

	@Transactional
	@PostMapping("/save")
	public ResponseEntity<MessageResponse> saveCommodity(@RequestBody @Valid CommodityDto commodityDto,
			BindingResult result) {

		log.info("RECEIVED PARAMETERS: '" + commodityDto + "'");

		// MessageResponse message = new MessageResponse();
		/*
		 * if (result.hasErrors()) { return
		 * restControllerConfigService.bindingResult(result, log); }
		 */

		boolean isCommodityIsvailableByName = commodityService.isCommodityIsvailableByName(commodityDto);

		if (!isCommodityIsvailableByName) {
			return ResponseEntity.ok(new MessageResponse(true, "Nombre de mercancia no disponible"));
		}

		boolean validateDateOfAdmission = validateDateOfAdmission(commodityDto);

		if (!validateDateOfAdmission) {
			return ResponseEntity.ok(new MessageResponse(true, "Fecha de ingreso es superior a la fecha actual"));
		}

		commodityService.saveCommodity(commodityDto);

		// log.info("RESPONSE DATA: '" + message + "'");

		return ResponseEntity.ok(new MessageResponse(false, "Mercancía guardada"));
	}

	@Transactional
	@PutMapping("/update/{id}")
	public ResponseEntity<MessageResponse> updateCommodity(@PathVariable("id") Long id,
			@RequestBody @Valid CommodityDto commodityDto, BindingResult result) throws ResourceNotFoundException {

		log.info("WEB CLIENT - RECEIVED PARAMETERS: 'id: " + id + "', " + commodityDto + "'");

		Optional.ofNullable(commodityService.getCommodityById(id))
				.orElseThrow(() -> new ResourceNotFoundException("Mercancía no encontrada para este id :: " + id));

		CommodityDto commodityDto2 = commodityService.getCommodityById(id);
		String name = commodityDto.getName().trim();

		if (!commodityDto2.getName().equals(name)) {

			boolean isCommodityIsvailableByName = commodityService.isCommodityIsvailableByName(commodityDto);

			if (!isCommodityIsvailableByName) {
				return ResponseEntity.ok(new MessageResponse(true, "Nombre de mercancia no disponible"));
			}
		}

		boolean validateDateOfAdmission = validateDateOfAdmission(commodityDto);

		if (!validateDateOfAdmission) {
			return ResponseEntity.ok(new MessageResponse(true, "Fecha de ingreso es superior a la fecha actual"));
		}

		commodityService.updateCommodity(id, commodityDto);
		updateHistoryCommodityService.saveUpdateHistoryCommodity(commodityDto);

		MessageResponse message = new MessageResponse(false, "Mercancía actualizada");

		log.info("RESPONSE DATA: '" + message + "'");

		return ResponseEntity.ok(message);
	}

	@DeleteMapping("/delete/{id}/{creatorUserId}")
	public ResponseEntity<MessageResponse> deleteCommodity(@PathVariable("id") Long id,
			@PathVariable("creatorUserId") Long creatorUserId) throws ResourceNotFoundException {

		log.info("RECEIVED PARAMETERS: 'id: " + id + "', 'creatorUserId: " + creatorUserId + "'");

		CommodityDto commodityDto = Optional.ofNullable(commodityService.getCommodityById(id))
				.orElseThrow(() -> new ResourceNotFoundException("Mercancía no encontrada para este id :: " + id));

		UserDto userDto = Optional.ofNullable(userService.getUserById(creatorUserId))
				.orElseThrow(() -> new ResourceNotFoundException("Mercancía no encontrada para este id :: " + id));

		if (!commodityDto.getCreatorUser().getId().equals(userDto.getId())) {
			return ResponseEntity
					.ok(new MessageResponse(true, "No puede eliminar este registro, no es el usuario creador"));
		}

		commodityService.deleteCommodity(id);
		MessageResponse message = new MessageResponse(false, "Mercancía eliminada");

		log.info("RESPONSE DATA: '" + message + "'");

		return ResponseEntity.ok(message);
	}

	private boolean validateDateOfAdmission(CommodityDto commodityDto) {

		Date dateOfAdmission = commodityDto.getDateOfAdmission();

		if (dateOfAdmission.before(new Date()) || dateOfAdmission.equals(new Date())) {
			return true;
		}

		return false;
	}

}
