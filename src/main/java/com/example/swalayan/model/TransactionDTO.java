package com.example.swalayan.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class TransactionDTO {
    private Long id_trans;
    private int year;
    private int month;
    private int day;
    private String time;
    private double total_amount;
    private Long nip;
    private List<TransactionItemDTO> transactionItems;
}
