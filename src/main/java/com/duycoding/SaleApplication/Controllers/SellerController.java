package com.duycoding.SaleApplication.Controllers;

import com.duycoding.SaleApplication.Entities.Seller;
import com.duycoding.SaleApplication.Services.SellerService;
import com.duycoding.SaleApplication.utils.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    @ApiMessage("Get all sellers")
    public List<Seller> getAllSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/{id}")
    @ApiMessage("Get a seller")
    public ResponseEntity<?> getSeller(@PathVariable long id) {
        return ResponseEntity.ok(sellerService.getSeller(id));
    }

    @PostMapping
    @ApiMessage("Create a seller")
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        Seller savedSeller = sellerService.saveSeller(seller);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSeller);
    }

    @PutMapping("/{id}")
    @ApiMessage("Update a seller")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @RequestBody Seller seller) {
        return ResponseEntity.ok(sellerService.updateSeller(id, seller));
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Delete a seller")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }

}