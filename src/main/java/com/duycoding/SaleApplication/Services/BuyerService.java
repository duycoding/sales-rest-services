package com.duycoding.SaleApplication.Services;

import com.duycoding.SaleApplication.Entities.Buyer;
import com.duycoding.SaleApplication.Entities.Seller;
import com.duycoding.SaleApplication.Repositories.BuyerRepository;
import com.duycoding.SaleApplication.dto.BuyerDTO;
import com.duycoding.SaleApplication.dto.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerService {
    private final BuyerRepository buyerRepository;


    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public PaginatedResponse<BuyerDTO> getAllBuyer(Pageable pageable) {
        Page<Buyer> buyerPage = buyerRepository.findAll(pageable);

        List<BuyerDTO> buyerDTOS = buyerPage.stream()
                .map(buyer -> new BuyerDTO(buyer.getId(), buyer.getName(), buyer.getEmail()))
                .toList();

        return new PaginatedResponse<>(
                buyerPage.getNumber() + 1,
                buyerPage.getTotalPages(),
                buyerPage.getTotalElements(),
                buyerDTOS
        );
    }

    public Buyer createBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    public String updateBuyer(long id, Buyer request) {
        Buyer buyer = buyerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));
        buyer.setName(request.getName());
        buyer.setEmail(request.getEmail());
        buyerRepository.save(buyer);
        return "Updated";
    }

    public String deleteBuyer(long id) {
        buyerRepository.deleteById(id);
        return "Deleted";
    }

    public BuyerDTO getBuyer(long id) {
        return buyerRepository.findById(id)
                .map(buyer -> new BuyerDTO(buyer.getId(), buyer.getEmail(), buyer.getName()))
                .orElseThrow(() -> new RuntimeException("Buyer with ID " + id + " not found"));
    }
}
