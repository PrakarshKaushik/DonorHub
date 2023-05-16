package com.capstone.donorhub.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.donorhub.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
	List<Orders> findAllOrdersByUser(int ngoId);

}
