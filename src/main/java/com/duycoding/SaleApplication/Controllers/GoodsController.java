package com.duycoding.SaleApplication.Controllers;

import com.duycoding.SaleApplication.Entities.Goods;
import com.duycoding.SaleApplication.Services.GoodsService;
import com.duycoding.SaleApplication.utils.annotation.ApiMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping
    @ApiMessage("Get all goods")
    public List<Goods> getAllGoods() {
        return goodsService.getAllGoods();
    }

    @PostMapping
    @ApiMessage("Create a goods")
    public ResponseEntity<Goods> createGoods(@RequestBody Goods goods) {
        Goods goodsRes = goodsService.createGoods(goods);
        return ResponseEntity.status(HttpStatus.CREATED).body(goodsRes);
    }

    @PutMapping("/{id}")
    @ApiMessage("Update a goods")
    public ResponseEntity<String> updateGoods(@PathVariable Long id, @RequestBody Goods goods) {
        return ResponseEntity.ok(goodsService.updateGoods(id, goods));
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Delete a goods")
    public ResponseEntity<Void> deleteGoods(@PathVariable("id") Long id) {
        goodsService.deleteGoods(id);
        return ResponseEntity.noContent().build();
    }
}
