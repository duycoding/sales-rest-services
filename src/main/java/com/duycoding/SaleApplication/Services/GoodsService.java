package com.duycoding.SaleApplication.Services;

import com.duycoding.SaleApplication.Entities.Goods;
import com.duycoding.SaleApplication.Repositories.GoodsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    public final GoodsRepository goodsRepository;

    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    public Goods createGoods(Goods request) {
        Goods goods = goodsRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Goods not found"));
        goods.setName(request.getName());
        goods.setSeller(request.getSeller());
        goods.setPrice(request.getPrice());
        return goodsRepository.save(goods);
    }

    public String updateGoods(long id, Goods request) {
        Goods goods = goodsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Good not found"));
        goods.setName(request.getName());
        goods.setSeller(request.getSeller());
        goods.setPrice(request.getPrice());
        return "Updated";
    }

    public String deleteGoods(long id) {
        goodsRepository.deleteById(id);
        return "Deleted";
    }
}
