package com.capstone.donorhub.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.donorhub.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

	@Query(value = "SELECT * FROM orders WHERE ngo_id =4", nativeQuery = true)
	List<Orders> findAllOrdersByNgoId(int ngoId);

	List<Orders> findAllByUser(int ngoId);

	@Query(value = "select * from orders where ngo_id=:id", nativeQuery = true)
	public List<Orders> ngoOrders(int id);

}
