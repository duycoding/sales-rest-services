package com.duycoding.SaleApplication.Repositories;

import com.duycoding.SaleApplication.Entities.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    List<Goods> findByName(String name);

}
