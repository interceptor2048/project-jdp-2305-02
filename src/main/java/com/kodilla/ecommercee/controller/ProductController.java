package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok(productMapper.mapToProductDtoList(productList));
    }
    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.getProduct(productId)));
    }
    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) throws ProductNotFoundException {
        productService.deleteById(productId);
        return ResponseEntity.ok().build();
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        productService.saveProduct(product);
        return ResponseEntity.ok().build();
    }
    @PatchMapping(path = "{productToUpdateId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productToUpdateId, @RequestBody ProductDto updatedProductDto) throws ProductNotFoundException {
        productService.checkIfProductExists(productToUpdateId);
        Product productToUpdate = productMapper.mapToProduct(updatedProductDto);
        productService.saveProduct(productToUpdate);
        return ResponseEntity.ok(productMapper.mapToProductDto(productToUpdate));
    }
    @PutMapping(path = "{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateAndNewProduct(@PathVariable Long productId, @RequestBody ProductDto newProductDto) throws ProductNotFoundException {
        productService.checkIfProductExists(productId);
        productService.deleteById(productId);
        Product newProduct = productMapper.mapToProduct(newProductDto);
        productService.saveProduct(newProduct);
        return ResponseEntity.ok(productMapper.mapToProductDto(newProduct));
    }
}
