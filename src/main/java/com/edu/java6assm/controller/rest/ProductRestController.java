package com.edu.java6assm.controller.rest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.java6assm.entity.Product;
import com.edu.java6assm.service.ProductService;

@CrossOrigin("*")
@RestController
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping("/rest/products")
    public ResponseEntity<List<Product>> getAll(@RequestParam("sort") Optional<String> sort) {
        if (sort.isPresent()) {
            if (sort.get().equalsIgnoreCase("all")) {
                return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
            }
            if (sort.get().equalsIgnoreCase("price_asc")) {
                return new ResponseEntity<>(productService.findAll(Sort.by("price").ascending()),
                        HttpStatus.OK);
            }
            if (sort.get().equalsIgnoreCase("price_desc")) {
                return new ResponseEntity<>(productService.findAll(Sort.by("price").descending()),
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/rest/products/{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") Integer id) {
        try {
            Product product = productService.findById(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/rest/products")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(productService.create(product));
    }

    @PutMapping("/rest/products/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Integer id, @RequestBody Product product) {
        try {
            Product existProduct = productService.findById(id);
            productService.update(product);
            return new ResponseEntity<Product>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        // return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("/rest/products/{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }

    @GetMapping("/rest/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query) {
        return ResponseEntity.ok(productService.searchProducts(query));
    }

    @GetMapping("/rest/products/category/{categoryId}")
    public ResponseEntity<List<Product>> findProductByCategory(
            @PathVariable("categoryId") Optional<Integer> categoryId) {
        return ResponseEntity.ok(productService.findByCategoryId(categoryId.get()));
    }

}
