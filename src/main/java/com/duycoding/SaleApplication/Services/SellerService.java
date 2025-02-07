package com.duycoding.SaleApplication.Services;

import com.duycoding.SaleApplication.Entities.Seller;
import com.duycoding.SaleApplication.Repositories.SellerRepository;

import com.duycoding.SaleApplication.dto.PaginatedResponse;
import com.duycoding.SaleApplication.dto.SellerDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public PaginatedResponse<SellerDTO> getAllSellers(Pageable pageable) {
        Page<Seller> sellerPage = sellerRepository.findAll(pageable);

        List<SellerDTO> sellerDTOS = sellerPage.stream()
                .map(seller -> new SellerDTO(seller.getId(), seller.getName(), seller.getEmail()))
                .toList();

        return new PaginatedResponse<>(
                sellerPage.getNumber() + 1,
                sellerPage.getTotalPages(),
                sellerPage.getTotalElements(),
                sellerDTOS
        );
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

    public Object getSeller(long id) {
        return sellerRepository.findById(id)
                .map(seller -> new SellerDTO(seller.getId(), seller.getName(), seller.getEmail()))
                .orElseThrow(() -> new RuntimeException("Seller with ID " + id + " not found"));
    }
}
