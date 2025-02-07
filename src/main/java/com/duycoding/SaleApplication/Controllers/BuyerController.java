package com.duycoding.SaleApplication.Controllers;

import com.duycoding.SaleApplication.Entities.Buyer;
import com.duycoding.SaleApplication.Services.BuyerService;
import com.duycoding.SaleApplication.dto.BuyerDTO;
import com.duycoding.SaleApplication.dto.PaginatedResponse;
import com.duycoding.SaleApplication.utils.annotation.ApiMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buyers")
public class BuyerController {
    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping
    @ApiMessage("Get all buyers")
    public PaginatedResponse<BuyerDTO> getAllBuyers(Pageable pageable) {
        return buyerService.getAllBuyer(pageable);
    }

    @GetMapping("/{id}")
    @ApiMessage("Get buyer with id")
    public ResponseEntity<?> getBuyer(@PathVariable Long id) {
        return ResponseEntity.ok(buyerService.getBuyer(id));
    }

    @PostMapping
    @ApiMessage("Create a buyer")
    public ResponseEntity<Buyer> createBuyer(@RequestBody Buyer buyer) {
        Buyer savedBuyer = buyerService.createBuyer(buyer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBuyer);
    }

    @PutMapping("/{id}")
    @ApiMessage("Update a buyer")
    public ResponseEntity<String> updateBuyer(@PathVariable("id") Long id, @RequestBody Buyer buyer) {
        return ResponseEntity.ok(buyerService.updateBuyer(id, buyer));
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Delete a buyer")
    public ResponseEntity<?> deleteBuyer(@PathVariable Long id) {
        buyerService.deleteBuyer(id);
        return ResponseEntity.noContent().build();
    }
}
