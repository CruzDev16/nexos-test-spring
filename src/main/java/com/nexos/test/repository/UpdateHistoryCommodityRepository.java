package com.nexos.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexos.test.entity.UpdateHistoryCommodity;

@Repository("updateHistoryCommodityRepository")
public interface UpdateHistoryCommodityRepository extends JpaRepository<UpdateHistoryCommodity, Long> {

}
