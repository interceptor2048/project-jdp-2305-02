package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class ItemDto {

    private Long itemId;
    private Long cartId;
    private List<Product> product;

    public ItemDto(Long itemId, List<Product> product, Long cartId) {
        this.itemId = itemId;
        this.product = product;
        this.cartId=cartId;
    }
}
