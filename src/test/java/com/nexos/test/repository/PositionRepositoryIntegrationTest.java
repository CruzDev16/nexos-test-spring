package com.nexos.test.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nexos.test.entity.Position;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql("/sql_test/position_test.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PositionRepositoryIntegrationTest {

	@Autowired
	PositionRepository positionRepository;

	@Test
	public void getPositionShouldSize3AndEqualsName() {

		List<Position> positions = positionRepository.getPositions();

		assertThat(positions).hasSize(3);

		String nameExpected = "Medico";
		String nameActual = positions.get(1).getName();

		assertEquals(nameExpected, nameActual, "Names do not match");
	}

	@Test
	public void itShouldSavePosition() {

		Position position = new Position();

		position.setId(Long.valueOf(4));
		position.setName("test position");
		position = positionRepository.save(position);
		assertThat(positionRepository.findById(position.getId()).get()).isEqualTo(position);
	}

}
