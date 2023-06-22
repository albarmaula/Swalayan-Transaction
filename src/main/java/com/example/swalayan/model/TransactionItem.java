package com.example.swalayan.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TransactionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_item;
    private int quantity;
    private double sub_total;
    private Long id_product;
}
