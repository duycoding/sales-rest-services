package com.duycoding.SaleApplication.Repositories;

import com.duycoding.SaleApplication.Entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {}