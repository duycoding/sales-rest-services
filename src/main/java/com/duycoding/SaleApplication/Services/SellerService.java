package com.duycoding.SaleApplication.Services;

import com.duycoding.SaleApplication.Entities.Seller;
import com.duycoding.SaleApplication.Repositories.SellerRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller saveSeller(Seller seller) {
        sellerRepository.save(seller);
        return seller;
    }

    public Seller updateSeller(Long id, Seller seller) {
        return sellerRepository.findById(id)
                .map(existingSeller -> {
                    existingSeller.setName(seller.getName());
                    existingSeller.setEmail(seller.getEmail());
                    return sellerRepository.save(existingSeller);
                })
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + id));
    }


    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }

    public Optional<Seller> getSeller(long id) {
        return sellerRepository.findById(id);
    }
}
