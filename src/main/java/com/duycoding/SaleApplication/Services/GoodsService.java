package com.duycoding.SaleApplication.Services;

import com.duycoding.SaleApplication.Entities.Goods;
import com.duycoding.SaleApplication.Entities.Seller;
import com.duycoding.SaleApplication.Repositories.GoodsRepository;
import com.duycoding.SaleApplication.Repositories.SellerRepository;
import com.duycoding.SaleApplication.dto.BuyerDTO;
import com.duycoding.SaleApplication.dto.GoodsDTO;
import com.duycoding.SaleApplication.dto.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsService {
    public final GoodsRepository goodsRepository;

    public final SellerRepository sellerRepository;

    public GoodsService(GoodsRepository goodsRepository, SellerRepository sellerRepository) {
        this.goodsRepository = goodsRepository;
        this.sellerRepository = sellerRepository;
    }

    public PaginatedResponse<GoodsDTO> getAllGoods(Pageable pageable) {
        Page<Goods> goodsPage = goodsRepository.findAll(pageable);

        List<GoodsDTO> goodsDTOS = goodsPage.stream()
                .map(goods -> new GoodsDTO(goods.getId(), goods.getName(), goods.getSeller().getId(), goods.getPrice(), goods.getStock(), goods.getCategory()))
                .toList();

        return new PaginatedResponse<>(
                goodsPage.getNumber() + 1,
                goodsPage.getTotalPages(),
                goodsPage.getTotalElements(),
                goodsDTOS
        );
    }

    public Goods createGoods(Long sellerId, Goods request) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Goods goods = null;

        if (request.getId() != null) {
            goods = goodsRepository.findById(request.getId()).orElse(null);
        }

        if (goods == null) {
            List<Goods> goodsList = goodsRepository.findByName(request.getName());

            if (goodsList.size() == 1) {
                goods = goodsList.get(0);
            } else if (goodsList.size() > 1) {
                throw new RuntimeException("Multiple goods found with the same name. Please specify an ID.");
            }
        }

        if (goods != null) {
            goods.setStock(goods.getStock() + request.getStock());
            goods.setPrice(request.getPrice());
            goods.setCategory(request.getCategory());
        } else {
            goods = new Goods();
            goods.setName(request.getName());
            goods.setPrice(request.getPrice());
            goods.setStock(request.getStock());
            goods.setCategory(request.getCategory());
            goods.setSeller(seller);
        }

        return goodsRepository.save(goods);
    }




    public GoodsDTO updateGoods(long id, GoodsDTO request) {
        Goods goods = goodsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Good not found"));

        goods.setName(request.getName());
        goods.setPrice(request.getPrice());
        goods.setStock(request.getStock());
        goods.setCategory(request.getCategory());

        // Cập nhật seller nếu sellerId hợp lệ
        if (request.getSellerId() != null) {
            Seller seller = sellerRepository.findById(request.getSellerId())
                    .orElseThrow(() -> new RuntimeException("Seller not found"));
            goods.setSeller(seller);
        }

        // Lưu lại bản ghi sau khi cập nhật
        Goods updatedGoods = goodsRepository.save(goods);

        // Chuyển đổi `Goods` thành `GoodsDTO` để trả về đúng format JSON
        return new GoodsDTO(
                updatedGoods.getId(),
                updatedGoods.getName(),
                updatedGoods.getSeller().getId(),
                updatedGoods.getPrice(),
                updatedGoods.getStock(),
                updatedGoods.getCategory()
        );
    }


    public String deleteGoods(long id) {
        goodsRepository.deleteById(id);
        return "Deleted";
    }
}
