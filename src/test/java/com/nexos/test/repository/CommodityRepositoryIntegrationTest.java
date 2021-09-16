package com.nexos.test.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nexos.test.entity.Commodity;
import com.nexos.test.entity.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql("/sql_test/commodity_test.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CommodityRepositoryIntegrationTest {

	@Autowired
	CommodityRepository commodityRepository;

	@Test
	public void itShouldSaveCommodity() {

		User user = new User();
		user.setId(Long.valueOf(1));

		Commodity commodity = new Commodity();

		commodity.setId(Long.valueOf(4));
		commodity.setName("test name");
		commodity.setProduct("test product");
		commodity.setQuantity(10);
		commodity.setDateOfAdmission(new Date());
		commodity.setCreatorUser(user);

		commodity = commodityRepository.save(commodity);
		assertThat(commodityRepository.findById(commodity.getId()).get()).isEqualTo(commodity);
	}

}
