package com.capstone.donorhub.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.donorhub.entity.Items;

@Repository
public interface ItemRepository extends JpaRepository<Items, Integer> {
	public List<Items> findByItemName(String name);

	@Query(value = "select * from items where user_id=:id", nativeQuery = true)
	public List<Items> donorItems(int id);

}
