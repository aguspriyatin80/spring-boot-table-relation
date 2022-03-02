package com.crud.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

import com.crud.basic.entity.Category;
import com.crud.basic.repository.CategoryRepository;

@Slf4j
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    private ResponseEntity<List<Category>> getAllCategories(Category category){
        try{
            List<Category> categories = categoryRepository.findAll();
            if(categories == null || categories.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
            
        } catch(Exception e){
            log.error("Failed retrieving data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<Category>> getOneCategory(@PathVariable("id") String id){
        try{
            Optional<Category> category = categoryRepository.findById(Long.valueOf(id));
            if(category == null || category.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(category, HttpStatus.OK);
            
        } catch(Exception e){
            log.error("Failed retrieving data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    private ResponseEntity<Category> saveCategory(@RequestBody Category category){
        try{
            Category existCategory = categoryRepository.findByName(category.getName());
            if(existCategory == null) {
                Category newCategory = categoryRepository.save(category);
                return new ResponseEntity<>(newCategory, HttpStatus.CREATED);    
            }
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch(Exception e){
            log.error("Failed creating data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    private ResponseEntity<Category> updateCategory(@RequestBody Category category){
        try{
            Category updateCategory = categoryRepository.save(category);
            return new ResponseEntity<>(updateCategory, HttpStatus.OK);                    
        } catch(Exception e){
            log.error("Failed creating data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    private ResponseEntity<String> deleteCategory(@RequestParam String id){        
        try{
            Category existingCategory = categoryRepository.getById(Long.valueOf(id));
            if(existingCategory != null) {
                categoryRepository.deleteById(Long.valueOf(id));
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(Exception e){
            log.error("Failed deleting data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
