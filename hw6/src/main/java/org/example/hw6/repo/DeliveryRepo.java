package org.example.hw6.repo;

import org.example.hw6.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepo extends JpaRepository<Delivery, Long> {
    List<Delivery> findByProductIdOrderByCreatedAtDesc(Long aLong);
}
