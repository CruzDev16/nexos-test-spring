package com.nexos.test.restController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexos.test.dto.CommodityDto;
import com.nexos.test.dto.UserDto;
import com.nexos.test.service.CommodityService;
import com.nexos.test.service.UpdateHistoryCommodityService;
import com.nexos.test.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommodityRestController.class)
@ActiveProfiles("test")
public class CommodityRestControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	@Qualifier("commodityServiceImpl")
	CommodityService commodityService;

	@MockBean
	@Qualifier("updateHistoryCommodityServiceImpl")
	UpdateHistoryCommodityService updateHistoryCommodityService;

	@MockBean
	@Qualifier("userServiceImpl")
	UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void itShouldReturnSaveCommodity() throws Exception {

		UserDto user = new UserDto();
		user.setId(Long.valueOf(1));

		CommodityDto commodity = new CommodityDto();

		commodity.setId(Long.valueOf(1));
		commodity.setName("test name");
		commodity.setProduct("test product");
		commodity.setQuantity(10);
		commodity.setDateOfAdmission(new Date());
		commodity.setCreatorUser(user);

		Mockito.when(commodityService.saveCommodity(commodity)).thenReturn(commodity);

		mockMvc.perform(post("/api/commodity/save").contentType("application/json")
				.content(objectMapper.writeValueAsString(commodity))).andExpect(status().isOk());
	}

}
