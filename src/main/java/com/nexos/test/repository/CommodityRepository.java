package com.nexos.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nexos.test.entity.Commodity;

@Repository("commodityRepository")
public interface CommodityRepository extends JpaRepository<Commodity, Long> {

	@Query("FROM Commodity c WHERE c.name = :name")
	public Optional<Commodity> getCommodityOrderByName(String name);

}
