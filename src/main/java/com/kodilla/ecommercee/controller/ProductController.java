package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

    List<ProductDto> testProductList = new ArrayList<ProductDto>() {
        {
            add(new ProductDto(1L, "kurtka zimowa", "Pellentesque(...)", new BigDecimal(100), 1L));
            add(new ProductDto(2L, "płaszcz", "Pellentesque(...)", new BigDecimal(150), 1L));
            add(new ProductDto(3L, "buty", "Pellentesque(...)", new BigDecimal(100), 4L));
            add(new ProductDto(4L, "rękawiczki", "Pellentesque(...)", new BigDecimal(50), 2L));
        }
    };

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(testProductList);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        for (ProductDto product : testProductList) {
            if (product.getProductId().equals(productId)) {
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        for (ProductDto product : testProductList) {
            if (product.getProductId().equals(productId)) {
                testProductList.remove(product);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) {
        testProductList.add(productDto);

        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productNew) {
        for (ProductDto product : testProductList) {
            if (product.getProductId().equals(productId)) {
                testProductList.remove(product);
                testProductList.add(productNew);
                return ResponseEntity.ok(productNew);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
