package com.example.swalayan.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_trans;

    private int year;
    private int month;
    private int day;
    private String time;
    private int total_amount;

    @Column (name = "cashier", nullable = false)
    private long nip;

    public Transaction() {
    }

    public Transaction(Long id_trans, int year, int month, int day, String time, int total_amount, long nip) {
        this.id_trans = id_trans;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.total_amount = total_amount;
        this.nip = nip;
    }

    public Long getId_trans() {
        return id_trans;
    }

    public void setId_trans(Long id_trans) {
        this.id_trans = id_trans;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public long getNip() {
        return nip;
    }

    public void setNip(long nip) {
        this.nip = nip;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
