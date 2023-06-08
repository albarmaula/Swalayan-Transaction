package com.example.swalayan.model;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class TransactionMapper {
    private final ModelMapper modelMapper;

    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
//        transactionDTO.setId_trans(transaction.getId_trans());
        transactionDTO.setYear(transaction.getYear());
        transactionDTO.setMonth(transaction.getMonth());
        transactionDTO.setDay(transaction.getDay());
        Employee employee = new Employee();
        employee.setNip(transaction.getNip());
        transactionDTO.setEmployee(employee);
        return transactionDTO;
    }

    public Transaction toEntity(TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        transaction.setYear(transactionDTO.getYear());
        transaction.setMonth(transactionDTO.getMonth());
        transaction.setDay(transactionDTO.getDay());

//        LocalTime currentTime = LocalTime.now();
//        transaction.setTime(currentTime.toString());

        transaction.setNip(transactionDTO.getEmployee().getNip());
        return transaction;
    }
}

