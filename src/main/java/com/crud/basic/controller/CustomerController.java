package com.crud.basic.controller;

import java.util.List;
import java.util.Optional;

import com.crud.basic.entity.Customer;
import com.crud.basic.repository.CustomerRepository;

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

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class CustomerController {
    
    @Autowired
    CustomerRepository  customerRepository;

    @GetMapping("/customers")
    private ResponseEntity<List<Customer>> getAllCustomers(Customer customer){
        try{
            List<Customer> customers = customerRepository.findAll();
            if(customers == null || customers.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
                return new ResponseEntity<>(customers,HttpStatus.OK);
        } catch(Exception e) {
            log.error("Failed retrieving data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/{id}")
    private ResponseEntity<Optional<Customer>> getOneCustomers(@PathVariable("id") Long id){
        try{
            Optional<Customer> customer = customerRepository.findById(id);
            if(customer == null || customer.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
                return new ResponseEntity<>(customer,HttpStatus.OK);
        } catch(Exception e) {
            log.error("Failed retrieving data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers")
    private ResponseEntity<String> deleteCustomer(@RequestParam String id){
        try{
            Customer customer = customerRepository.getById(Long.valueOf(id));
            if(customer == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
                customerRepository.deleteById(Long.valueOf(id));
                return new ResponseEntity<>("Deleted",HttpStatus.OK);
        } catch(Exception e) {
            log.error("Failed deleting data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/customers")
    private ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){        
        try{
            Customer newCustomer = customerRepository.save(customer);
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        } catch(Exception e){
            log.error("Failed creating data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers")
    private ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){        
        try{
            Customer newCustomer = customerRepository.save(customer);
            return new ResponseEntity<>(newCustomer, HttpStatus.OK);
        } catch(Exception e){
            log.error("Failed creating data", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
