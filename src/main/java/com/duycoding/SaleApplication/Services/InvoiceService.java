package com.duycoding.SaleApplication.Services;

import com.duycoding.SaleApplication.Entities.Buyer;
import com.duycoding.SaleApplication.Entities.Goods;
import com.duycoding.SaleApplication.Entities.Invoice;
import com.duycoding.SaleApplication.Entities.Seller;
import com.duycoding.SaleApplication.Repositories.BuyerRepository;
import com.duycoding.SaleApplication.Repositories.GoodsRepository;
import com.duycoding.SaleApplication.Repositories.InvoiceRepository;
import com.duycoding.SaleApplication.Repositories.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private final GoodsRepository goodsRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, BuyerRepository buyerRepository, SellerRepository sellerRepository, GoodsRepository goodsRepository) {
        this.invoiceRepository = invoiceRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.goodsRepository = goodsRepository;
    }

    public Invoice createInvoice(Long buyerId, Long sellerId, List<Long> goodsIds) {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        List<Goods> goodsList = goodsRepository.findAllById(goodsIds);

//        double totalAmount = goodsList.stream().mapToDouble(Goods::getPrice).sum();
        double totalAmount = 0;

        Invoice invoice = new Invoice();
        invoice.setBuyer(buyer);
        invoice.setSeller(seller);
        invoice.setGoodsList(goodsList);
        invoice.setTotalAmount(totalAmount);

        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}