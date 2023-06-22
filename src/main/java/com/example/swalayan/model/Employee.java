package com.example.swalayan.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class Employee {
    private Long nip;
    private String name;
    private String username;
    private String password;
    private String address;
    private String number_phone;
    private String dept_name;
    private String position;
}
