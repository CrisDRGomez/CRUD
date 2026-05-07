package org.generation.products.controller;

import org.generation.products.model.Product;
import org.generation.products.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(
            @RequestBody Product product) {
                return ResponseEntity.status(201).body(productService.saveProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity <Product> getProductById(
            @PathVariable Long id) {
                return productService.getProductById(id)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {
                return productService.updateProduct(id, product)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
            }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id) {
                if (productService.deleteProduct(id)) {
                    return ResponseEntity.noContent().build();
                        } else {
                            return ResponseEntity.notFound().build();
                        }
            }
} // Class