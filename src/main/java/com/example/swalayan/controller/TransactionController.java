package com.example.swalayan.controller;

import com.example.swalayan.model.Transaction;
import com.example.swalayan.model.TransactionDTO;
import com.example.swalayan.model.TransactionMapper;
import com.example.swalayan.repository.TransactionRepository;
import com.example.swalayan.repository.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    @Autowired
    public TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody Transaction transaction) {
        TransactionDTO transactionDTO = transactionMapper.toDTO(transaction);
        TransactionDTO createdTransaction = transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

//    @PostMapping("/create") ##sama saja, tp di bodynya nanti pakai employee
//    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
//        TransactionDTO createdTransaction = transactionService.createTransaction(transactionDTO);
//        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable("id") Long id_trans) {
        transactionService.deleteTransaction(id_trans);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        TransactionDTO transaction = transactionService.getTransactionById(id);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/day/{day}")
    public ResponseEntity<List<TransactionDTO>> findTransactionsByDay(@PathVariable Integer day) {
        List<TransactionDTO> transactions = transactionService.findTransactionsByDay(day);
        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactions);

    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<TransactionDTO>> findTransactionsByMonth(@PathVariable Integer month) {
        List<TransactionDTO> transactions = transactionService.findTransactionsByMonth(month);
        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<TransactionDTO>> findTransactionsByYear(@PathVariable Integer year) {
        List<TransactionDTO> transactions = transactionService.findTransactionsByYear(year);
        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/date/{year}/{month}/{day}")
    public ResponseEntity<List<TransactionDTO>> findByYearOrMonthOrDay(@PathVariable Integer year, Integer month, Integer day) {
        List<TransactionDTO> transactions = transactionService.findByYearOrMonthOrDay(year, month, day);
        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/nip/{nip}")
    public ResponseEntity<List<TransactionDTO>> findTransactionsByNip(@PathVariable Long nip) {
        List<TransactionDTO> transactions = transactionService.findTransactionsByNip(nip);
        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactions);
    }

}
