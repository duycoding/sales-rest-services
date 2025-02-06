package com.duycoding.SaleApplication.Controllers;

import com.duycoding.SaleApplication.Entities.Invoice;
import com.duycoding.SaleApplication.Services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(
            @RequestParam Long buyerId,
            @RequestParam Long sellerId,
            @RequestBody List<Long> goodsIds
    ) {
        Invoice invoice = invoiceService.createInvoice(buyerId, sellerId, goodsIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.status(HttpStatus.OK).body(invoices);
    }
}

