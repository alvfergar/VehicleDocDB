package com.app.vehicledocdb.vehicledocdb.model;

/**
 * Created by Alvaro on 04/06/2016.
 */
public class Requirement {

    private String name;
    private String endDate;

    public Requirement(String name, String endDate) {
        this.name = name;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "name='" + name + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
