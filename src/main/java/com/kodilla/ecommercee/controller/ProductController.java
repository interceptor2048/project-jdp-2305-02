package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable Long productId) {
        return new ProductDto(1L,
                "kurtka zimowa",
                "Lorem Ipsum",
                new BigDecimal(100),
                1L);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createProduct() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable Long productId) {
        return new ProductDto(1L,
                "zimowa kurtka - zmiany",
                "Lorem Ipsum",
                new BigDecimal(120),
                1L);
    }
}
