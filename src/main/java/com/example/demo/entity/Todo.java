package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @ManyToOne
    private User user;

}
