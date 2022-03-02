package com.crud.basic.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//To use the @Data annotation you should add the Lombok dependency.

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;
    
    @OneToMany(mappedBy = "category")
    @JsonBackReference
    private List<Product> products;

}