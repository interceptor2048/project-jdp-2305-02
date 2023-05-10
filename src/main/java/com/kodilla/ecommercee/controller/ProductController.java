package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok(productMapper.mapToProductDtoList(productList));
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        if (productService.getProduct(productId) != null) {
            return ResponseEntity.ok(productMapper.mapToProductDto(productService.getProduct(productId)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        if (productService.getProduct(productId) != null) {
            productService.deleteById(productId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        productService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto newProductDto) {
        if (productService.getProduct(productId) != null) {
            productService.saveProduct(productMapper.mapToProduct(newProductDto));
            return ResponseEntity.ok(newProductDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
