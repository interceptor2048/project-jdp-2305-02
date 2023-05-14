package com.kodilla.ecommercee.mapper;



import com.kodilla.ecommercee.domain.Item;
import com.kodilla.ecommercee.dto.ItemDto;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemMapper {

    @Autowired
    CartService cartDbService;

    @Autowired
    ProductService productDbService;

    public  ItemDto mapToItemDto(final Item item) {
        return new ItemDto(
                item.getItemId(),
                item.getProduct(),
                item.getCart().getId()
        );
    }

    public Item mapToItem(final ItemDto itemDto) {
        return new Item(
                itemDto.getProduct(),
                cartDbService.getCartById(itemDto.getCartId())
        );
    }


    public List<Item> mapToItemList(final List<ItemDto> itemDtoList) {
        return itemDtoList
                .stream()
                .map(this::mapToItem)
                .collect(Collectors.toList());
    }


    public List<ItemDto> mapToItemDtoList(final List<Item> itemList) {
        return itemList
                .stream()
                .map(this::mapToItemDto)
                .collect(Collectors.toList());
    }


}
