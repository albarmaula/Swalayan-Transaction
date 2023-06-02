package com.example.swalayan.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nip;
    private String name;
    private int salary;
    private String number_phone;
    private String position;
    private String shop_name;

    public Employee() {
    }

    public Employee(Long nip, String name, int salary, String number_phone, String position, String shop_name) {
        this.nip = nip;
        this.name = name;
        this.salary = salary;
        this.number_phone = number_phone;
        this.position = position;
        this.shop_name = shop_name;
    }

    public Long getNip() {
        return nip;
    }

    public void setNip(Long nip) {
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getNumber_phone() {
        return number_phone;
    }

    public void setNumber_phone(String number_phone) {
        this.number_phone = number_phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getShop_name() {
        return shop_name;
    }
    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
