package com.duycoding.SaleApplication.Controllers;

import com.duycoding.SaleApplication.Services.InvoiceService;
import com.duycoding.SaleApplication.dto.InvoiceRequestDTO;
import com.duycoding.SaleApplication.dto.PaginatedResponse;
import com.duycoding.SaleApplication.dto.responseDTO.InvoiceResponse;
import com.duycoding.SaleApplication.utils.annotation.ApiMessage;
import org.springframework.data.domain.Pageable;
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
    @ApiMessage("Create an invoice")
    public ResponseEntity<InvoiceResponse> createInvoice(
            @RequestBody InvoiceRequestDTO request
    ) {
        InvoiceResponse invoice = invoiceService.createInvoice(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
    }


    @GetMapping
    @ApiMessage("Get all invoices")
    public ResponseEntity<PaginatedResponse<InvoiceResponse>> getAllInvoices(Pageable pageable) {
        PaginatedResponse<InvoiceResponse> invoices = invoiceService.getAllInvoices(pageable);
        return ResponseEntity.ok(invoices);
    }

}

