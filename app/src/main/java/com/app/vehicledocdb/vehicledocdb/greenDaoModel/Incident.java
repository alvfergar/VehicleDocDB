package com.app.vehicledocdb.vehicledocdb.greenDaoModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "INCIDENT".
 */
public class Incident {

    private Long id;
    private String incidentName;
    private String address;
    private Long date;
    private Long price;

    public Incident() {
    }

    public Incident(Long id) {
        this.id = id;
    }

    public Incident(Long id, String incidentName, String address, Long date, Long price) {
        this.id = id;
        this.incidentName = incidentName;
        this.address = address;
        this.date = date;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIncidentName() {
        return incidentName;
    }

    public void setIncidentName(String incidentName) {
        this.incidentName = incidentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
