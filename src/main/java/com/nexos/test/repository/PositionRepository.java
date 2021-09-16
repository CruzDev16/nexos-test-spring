package com.nexos.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nexos.test.entity.Position;

@Repository("positionRepository")
public interface PositionRepository extends JpaRepository<Position, Long> {

	@Query("FROM Position p ORDER BY p.name")
	public List<Position> getPositions();
	
}
