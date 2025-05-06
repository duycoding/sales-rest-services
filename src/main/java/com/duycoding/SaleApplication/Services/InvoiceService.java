package com.duycoding.SaleApplication.Services;

import com.duycoding.SaleApplication.Entities.*;
import com.duycoding.SaleApplication.Exception.CustomException.BuyerNotFoundException;
import com.duycoding.SaleApplication.Exception.CustomException.GoodsNotFoundException;
import com.duycoding.SaleApplication.Exception.CustomException.InsufficientStockException;
import com.duycoding.SaleApplication.Repositories.*;
import com.duycoding.SaleApplication.dto.GoodsDTO;
import com.duycoding.SaleApplication.dto.InvoiceItemDTO;
import com.duycoding.SaleApplication.dto.InvoiceRequestDTO;
import com.duycoding.SaleApplication.dto.PaginatedResponse;
import com.duycoding.SaleApplication.dto.responseDTO.InvoiceItemResponse;
import com.duycoding.SaleApplication.dto.responseDTO.InvoiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final BuyerRepository buyerRepository;
    private final GoodsRepository goodsRepository;
    private final InvoiceItemRepository invoiceItemRepository;

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            BuyerRepository buyerRepository,
            GoodsRepository goodsRepository,
            InvoiceItemRepository invoiceItemRepository) {
        this.invoiceRepository = invoiceRepository;
        this.buyerRepository = buyerRepository;
        this.goodsRepository = goodsRepository;
        this.invoiceItemRepository = invoiceItemRepository;
    }

//    @Transactional
//    public InvoiceResponse createInvoice(InvoiceRequestDTO request) {
//        Buyer buyer = buyerRepository.findById(request.getBuyerId())
//                .orElseThrow(() -> new RuntimeException("Buyer not found"));
//
//        // Lấy danh sách ID hàng hóa từ request
//        List<Long> goodsIds = request.getItems().stream()
//                .map(InvoiceItemDTO::getGoodsId)
//                .collect(Collectors.toList());
//
//        // Lấy danh sách hàng hóa từ DB
//        List<Goods> goodsList = goodsRepository.findAllById(goodsIds);
//
//        // Kiểm tra nếu có sản phẩm nào không tồn tại hoặc bị xóa mềm
//        if (goodsList.size() != goodsIds.size()) {
//            throw new RuntimeException("Some goods are invalid or unavailable.");
//        }
//
//        for (Goods goods : goodsList) {
//            if (goods.isDeleted()) {
//                throw new RuntimeException("Cannot add deleted goods to invoice: " + goods.getName());
//            }
//        }
//
//        // Tạo hóa đơn
//        Invoice invoice = new Invoice();
//        invoice.setBuyer(buyer);
//
//        Invoice finalInvoice = invoice;
//        List<InvoiceItem> invoiceItems = request.getItems().stream().map(itemDTO -> {
//            Goods goods = goodsList.stream()
//                    .filter(g -> g.getId().equals(itemDTO.getGoodsId()))
//                    .findFirst()
//                    .orElseThrow(() -> new RuntimeException("Invalid goods ID: " + itemDTO.getGoodsId()));
//
//            // Kiểm tra stock trước khi tạo hóa đơn
//            if (goods.getStock() < itemDTO.getQuantity()) {
//                throw new RuntimeException("Not enough stock for goods: " + goods.getName());
//            }
//
//            // Cập nhật stock
//            goods.setStock(goods.getStock() - itemDTO.getQuantity());
//
//            double priceAtPurchase = goods.getPrice();
//
//            InvoiceItem invoiceItem = new InvoiceItem();
//            invoiceItem.setInvoice(finalInvoice);
//            invoiceItem.setGoods(goods);
//            invoiceItem.setQuantity(itemDTO.getQuantity());
//            invoiceItem.setPriceAtPurchase(priceAtPurchase);
//
//            return invoiceItem;
//        }).collect(Collectors.toList());
//
//        invoice.setInvoiceItems(invoiceItems);
//        invoice.updateTotalAmount();
//
//        invoice = invoiceRepository.save(invoice);
//        invoiceItemRepository.saveAll(invoiceItems);
//
//        // Cập nhật lại stock vào database
//        goodsRepository.saveAll(goodsList);
//
//        return convertToInvoiceResponse(invoice);
//    }

    @Transactional
    public InvoiceResponse createInvoice(InvoiceRequestDTO request) {
        Buyer buyer = buyerRepository.findById(request.getBuyerId())
                .orElseThrow(() -> new BuyerNotFoundException(request.getBuyerId()));

        List<Long> goodsIds = request.getItems().stream()
                .map(InvoiceItemDTO::getGoodsId)
                .collect(Collectors.toList());

        List<Goods> goodsList = goodsRepository.findAllById(goodsIds);

        if (goodsList.size() != goodsIds.size()) {
            throw new GoodsNotFoundException(-1L); // ID -1 dùng để chỉ nhiều hàng hóa không hợp lệ
        }

        for (Goods goods : goodsList) {
            if (goods.isDeleted()) {
                throw new GoodsNotFoundException(goods.getId());
            }
        }

        Invoice invoice = new Invoice();
        invoice.setBuyer(buyer);

        Invoice finalInvoice = invoice;
        List<InvoiceItem> invoiceItems = request.getItems().stream().map(itemDTO -> {
            Goods goods = goodsList.stream()
                    .filter(g -> g.getId().equals(itemDTO.getGoodsId()))
                    .findFirst()
                    .orElseThrow(() -> new GoodsNotFoundException(itemDTO.getGoodsId()));

            if (goods.getStock() < itemDTO.getQuantity()) {
                throw new InsufficientStockException(goods.getName());
            }

            goods.setStock(goods.getStock() - itemDTO.getQuantity());

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoice(finalInvoice);
            invoiceItem.setGoods(goods);
            invoiceItem.setQuantity(itemDTO.getQuantity());
            invoiceItem.setPriceAtPurchase(goods.getPrice());

            return invoiceItem;
        }).collect(Collectors.toList());

        invoice.setInvoiceItems(invoiceItems);
        invoice.updateTotalAmount();

        invoice = invoiceRepository.save(invoice);
        invoiceItemRepository.saveAll(invoiceItems);
        goodsRepository.saveAll(goodsList);

        return convertToInvoiceResponse(invoice);
    }



    private InvoiceResponse convertToInvoiceResponse(Invoice invoice) {
        List<InvoiceItemResponse> itemResponses = invoice.getInvoiceItems().stream()
                .map(item -> new InvoiceItemResponse(
                        item.getGoods().getName(),
                        item.getQuantity(),
                        item.getPriceAtPurchase(),
                        item.getQuantity() * item.getPriceAtPurchase()
                ))
                .collect(Collectors.toList());

        return new InvoiceResponse(
                invoice.getId(),
                invoice.getBuyer().getName(),
                itemResponses,
                invoice.getTotalAmount(),
                invoice.getCreatedAt() // ✅ Thêm createdAt vào phản hồi
        );
    }

    public PaginatedResponse<InvoiceResponse> getAllInvoices(Pageable pageable) {
        Page<Invoice> invoicePage = invoiceRepository.findAll(pageable);
        List<InvoiceResponse> invoiceDTOS = invoicePage.stream()
                .map(invoice -> new InvoiceResponse(
                        invoice.getId(),
                        invoice.getBuyer().getName(),  // Lấy tên người mua thay vì truyền trực tiếp Buyer
                        invoice.getInvoiceItems().stream()
                                .distinct()
                                .map(item -> new InvoiceItemResponse(
                                        item.getGoods().getName(), // Lấy tên hàng hóa
                                        item.getQuantity(),
                                        item.getPriceAtPurchase(),
                                        item.getQuantity() * item.getPriceAtPurchase() // Tính tổng giá
                                )).toList(), // Chuyển đổi danh sách InvoiceItem thành InvoiceItemResponse
                        invoice.getTotalAmount(),
                        invoice.getCreatedAt()
                ))
                .toList();

        return new PaginatedResponse<>(
                invoicePage.getNumber() + 1,  // Đúng với số trang
                invoicePage.getTotalPages(),
                invoicePage.getTotalElements(),
                invoiceDTOS
        );
    }


}
