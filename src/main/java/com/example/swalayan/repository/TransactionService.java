package com.example.swalayan.repository;

import com.example.swalayan.model.Transaction;
import com.example.swalayan.model.TransactionDTO;
import com.example.swalayan.model.TransactionMapper;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    public final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;


    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Transactional
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        LocalDateTime currentDateTime = LocalDateTime.now();
        transaction.setYear(currentDateTime.getYear());
        transaction.setMonth(currentDateTime.getMonthValue());
        transaction.setDay(currentDateTime.getDayOfMonth());
        transaction.setHour(currentDateTime.getHour());
        transaction.setMinute(currentDateTime.getMinute());
        transaction.setSecond(currentDateTime.getSecond());
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDTO(savedTransaction);
    }

    @Transactional
    public TransactionDTO getTransactionById(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid transaction id: " + transactionId));
        return transactionMapper.toDTO(transaction);
    }

    @Transactional
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<TransactionDTO> findTransactionsByDay(int day) {
        List<Transaction> transactions = transactionRepository.findByDay(day);
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<TransactionDTO> findTransactionsByMonth(int month) {
        List<Transaction> transactions = transactionRepository.findByMonth(month);
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<TransactionDTO> findTransactionsByYear(int year) {
        List<Transaction> transactions = transactionRepository.findByYear(year);
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<TransactionDTO> findTransactionsByNip(long nip) {
        List<Transaction> transactions = transactionRepository.findByNip(nip);
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

}
