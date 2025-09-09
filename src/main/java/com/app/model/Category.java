package com.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jm_categ")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    private String name;

    // Getters and Setters
    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
