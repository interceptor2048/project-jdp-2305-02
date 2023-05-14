package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Item;
import com.kodilla.ecommercee.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Item getItem(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    public List<Item> getAllItems() {
        return Optional.ofNullable(itemRepository.findAll()).orElse(new ArrayList<>());
    }

    public List<Item> getAllItemsInCart(Long cartId) {
        return itemRepository.findByCart(cartId);
    }

    public Optional<Item> findById(final Long itemId) {
        return itemRepository.findById(itemId);
    }

    public void save(final Item item) {
        itemRepository.save(item);
    }

    public void deleteItemById(Long itemId) {
        itemRepository.deleteById(itemId);
    }

}
