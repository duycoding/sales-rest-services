package com.duycoding.SaleApplication.Repositories;

import com.duycoding.SaleApplication.Entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {}
