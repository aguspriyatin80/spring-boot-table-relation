package com.crud.basic.controller;

import java.util.List;
import java.util.Optional;

import com.crud.basic.entity.Supplier;
import com.crud.basic.repository.SupplierRepository;

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

@Slf4j

@RestController
@RequestMapping("api/v1/suppliers")
public class SupplierController {

    @Autowired
    SupplierRepository supplierRepository;
    
    @GetMapping
    private ResponseEntity<List<Supplier>> getAllSuppliers(Supplier supplier){
        try{
            List<Supplier> suppliers = supplierRepository.findAll();
            if(suppliers == null || suppliers.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(suppliers, HttpStatus.OK); 
        } catch (Exception e){
            log.error("Failed retrieving data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Supplier>> getOneSupplier(@PathVariable("id") Long id){
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(supplier == null || supplier.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplier, HttpStatus.OK);        
    }

    @PostMapping
    private ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier){
        try{
            Supplier newSupplier = supplierRepository.save(supplier);
            return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
        } catch (Exception e){
            log.error("Failed creating data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping
    private ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplier){
        try{
            Supplier newSupplier = supplierRepository.save(supplier);
            return new ResponseEntity<>(newSupplier, HttpStatus.OK);
        } catch (Exception e){
            log.error("Failed creating data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping
    private ResponseEntity<String> deleteProduct(@RequestParam String id){        
        try{
            Supplier existingSupplier = supplierRepository.getById(Long.valueOf(id));
            if(existingSupplier != null) {
                supplierRepository.deleteById(Long.valueOf(id));
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch(Exception e){
            log.error("Failed deleting data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
