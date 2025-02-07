package com.duycoding.SaleApplication.Repositories;

import com.duycoding.SaleApplication.Entities.Buyer;
import com.duycoding.SaleApplication.Entities.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Page<Buyer> findAll(Pageable pageable);
}
