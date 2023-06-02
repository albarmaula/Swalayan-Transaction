package com.example.swalayan.model;

import jakarta.persistence.Column;

public class TransactionDTO {
    private Long id_trans;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private int total_amount;
    private Employee employee;

    public TransactionDTO() {
    }

    public TransactionDTO(Long id_trans, int year, int month, int day, int hour, int minute, int second, int total_amount, Employee employee) {
        this.id_trans = id_trans;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.total_amount = total_amount;
        this.employee = employee;
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
