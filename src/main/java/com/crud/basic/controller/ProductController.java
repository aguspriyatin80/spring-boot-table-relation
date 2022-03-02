package com.crud.basic.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.crud.basic.entity.Product;
import com.crud.basic.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {
    
    @Autowired
    ProductRepository productRepository;
    
    @GetMapping
    private ResponseEntity<List<Product>> getAllProducts(Product product){        
        try{
            List<Product> products = productRepository.findAll();
            if(products == null || products.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(products,HttpStatus.OK);
        } catch(Exception e){
            log.error("Failed retrieving data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getOneProduct(@PathVariable("id") Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product == null || product.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);        
    }

    @GetMapping("/detail")
    public ResponseEntity<Optional<Product>> getDetailProduct(@RequestParam Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product == null || product.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);        
    }

    @PostMapping
    private ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product){        
        try{
            Product newProduct = productRepository.save(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch(Exception e){
            log.error("Failed creating data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    private ResponseEntity<Product> updateProduct(@RequestBody Product product){        
        try{
            Product newProduct = productRepository.save(product);
            return new ResponseEntity<>(newProduct, HttpStatus.OK);
        } catch(Exception e){
            log.error("Failed updating data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping
    private ResponseEntity<String> deleteProduct(@RequestParam String id){        
        try{
            productRepository.deleteById(Long.valueOf(id));
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch(Exception e){
            log.error("Failed deleting data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
