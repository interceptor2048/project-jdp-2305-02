package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> testProductList = new ArrayList<>();

        testProductList.add(new ProductDto(1L, "kurtka zimowa", "Pellentesque(...)", new BigDecimal(100), 1L));
        testProductList.add(new ProductDto(2L, "płaszcz", "Pellentesque(...)", new BigDecimal(150), 1L));
        testProductList.add(new ProductDto(3L, "buty", "Pellentesque(...)", new BigDecimal(100), 4L));
        testProductList.add(new ProductDto(4L, "rękawiczki", "Pellentesque(...)", new BigDecimal(50), 2L));

        return ResponseEntity.ok(testProductList);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(new ProductDto(
                5L,
                "sandały",
                "Pellentesque tempus interdum (...)",
                new BigDecimal(280),
                4L));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> createProduct() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId) {
        return ResponseEntity.ok().build();
    }
}
