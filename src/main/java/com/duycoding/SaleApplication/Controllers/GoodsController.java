package com.duycoding.SaleApplication.Controllers;

import com.duycoding.SaleApplication.Entities.Goods;
import com.duycoding.SaleApplication.Services.GoodsService;
import com.duycoding.SaleApplication.dto.ApiResponse;
import com.duycoding.SaleApplication.dto.GoodsDTO;
import com.duycoding.SaleApplication.dto.PaginatedResponse;
import com.duycoding.SaleApplication.utils.annotation.ApiMessage;
import org.springframework.data.domain.Pageable;
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
    public PaginatedResponse<GoodsDTO> getAllGoods(Pageable pageable) {
        return goodsService.getAllGoods(pageable);
    }

    @PostMapping("/{sellerId}")
    @ApiMessage("Create a goods")
    public ResponseEntity<Goods> createGoods(
            @PathVariable Long sellerId,
            @RequestBody Goods goodsDTO) {

        Goods goodsRes = goodsService.createGoods(sellerId, goodsDTO);
        return ResponseEntity.ok(goodsRes);
    }


    @PutMapping("/{id}")
    @ApiMessage("Update a goods")
    public ResponseEntity<GoodsDTO> updateGoods(@PathVariable Long id, @RequestBody GoodsDTO goods) {
        return ResponseEntity.ok(goodsService.updateGoods(id, goods));
    }


    @DeleteMapping("/{id}")
    @ApiMessage("Delete a goods")
    public ResponseEntity<Void> deleteGoods(@PathVariable("id") Long id) {
        goodsService.deleteGoods(id);
        return ResponseEntity.noContent().build();
    }
}
