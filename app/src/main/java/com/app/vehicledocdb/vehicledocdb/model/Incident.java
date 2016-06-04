package com.app.vehicledocdb.vehicledocdb.model;

/**
 * Created by Alvaro on 04/06/2016.
 */
public class Incident {

    private String incidentName;
    private String address;
    private String date;
    private Double price;

    public Incident(String incidentName, String address, String date, Double price) {
        this.incidentName = incidentName;
        this.address = address;
        this.date = date;
        this.price = price;
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

    @Override
    public String toString() {
        return "Incident{" +
                "incidentName='" + incidentName + '\'' +
                ", address='" + address + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                '}';
    }
}
