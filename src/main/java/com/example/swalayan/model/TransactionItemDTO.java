package com.example.swalayan.model;

import lombok.Data;

@Data
public class TransactionItemDTO {
    private Long id_item;
    private int quantity;
    private double sub_total;
    private Long id_product;
}
