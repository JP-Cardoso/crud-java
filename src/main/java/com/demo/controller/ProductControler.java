package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.product.Product;
import com.demo.domain.product.ProductRepository;
import com.demo.domain.product.RequestProduct;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/product")
@RestController
public class ProductControler {

  @Autowired
  private ProductRepository productRepository;

  @SuppressWarnings("rawtypes")
  @GetMapping
  public ResponseEntity getAllProduct() {
    var allProducts = productRepository.findAll();
    return ResponseEntity.ok(allProducts);
  }

  @PostMapping
  public ResponseEntity<String> createProduct(@RequestBody @Validated RequestProduct data) {
    Product newProduct = new Product(data);
    productRepository.save(newProduct);
    return ResponseEntity.ok("Deu certo");
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody @Validated RequestProduct data) {
    Product product = productRepository.getReferenceById(id);
    product.setName(data.name());
    product.setPrice_in_cents(data.priceInCents());
    return ResponseEntity.ok(product);
  }

}
