package com.concat.projetointegrador.controller;

import com.concat.projetointegrador.dto.ProductDTO;
import com.concat.projetointegrador.model.Product;
import com.concat.projetointegrador.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity <ProductDTO> findById(@PathVariable Long id) {
       return ResponseEntity.ok(productService.findById(id));

    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> findAll(){
        return ResponseEntity.ok(productService.findAll());

    }

    @PostMapping ("/products/new")
    public ResponseEntity<ProductDTO> create(@RequestBody Product product){
        return new ResponseEntity<>(productService.create(product),HttpStatus.CREATED);
    }

    @DeleteMapping ("/products/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping ("/products/{id}")
    public ResponseEntity <ProductDTO> update (@RequestBody Product product, @PathVariable Long id){
        return ResponseEntity.ok(productService.update(product, id));
    }
}
