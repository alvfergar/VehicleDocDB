package com.app.vehicledocdb.vehicledocdb.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "INCIDENT".
 */
public class Incident {

    private Long id;
    private String name;
    private String description;
    private String date;
    private Double price;

    public Incident() {
    }

    public Incident(Long id) {
        this.id = id;
    }

    public Incident(Long id, String name, String description, String date, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
