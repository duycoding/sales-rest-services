package com.duycoding.SaleApplication.Repositories;

import com.duycoding.SaleApplication.Entities.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findByNameAndIsDeletedFalse(String name);

    Optional<Goods> findByIdAndIsDeletedFalse(Long id);

    Page<Goods> findAllByIsDeletedFalse(Pageable pageable);
}
