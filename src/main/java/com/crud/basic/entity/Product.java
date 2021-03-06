package com.crud.basic.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message="Field address is required")
    private String name;
    private String description;
    private double price;

    @ManyToMany
    private List<Supplier> suppliers;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;
}