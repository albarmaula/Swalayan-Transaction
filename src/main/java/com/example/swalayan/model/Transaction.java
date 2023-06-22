package com.example.swalayan.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
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
    private double total_amount;
//    @Column(name = "nip")
//    private Employee employee;
    private Long nip;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_trans")
    private List<TransactionItem> transactionItems = new ArrayList<>();

    public void addTransactionItem(TransactionItem transactionItem) {
        transactionItems.add(transactionItem);
    }

    public void removeTransactionItem(TransactionItem transactionItem) {
        transactionItems.remove(transactionItem);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
