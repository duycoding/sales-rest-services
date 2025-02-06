package com.duycoding.SaleApplication.Services;

import com.duycoding.SaleApplication.Entities.Buyer;
import com.duycoding.SaleApplication.Repositories.BuyerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {
    private final BuyerRepository buyerRepository;


    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public List<Buyer> getAllBuyer() {
        return buyerRepository.findAll();
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
}
