package com.example.swalayan.controller;

import com.example.swalayan.model.Employee;
import com.example.swalayan.model.Transaction;
import com.example.swalayan.model.TransactionDTO;
import com.example.swalayan.repository.TransactionRepository;
import com.example.swalayan.repository.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable("id") Long id_trans) {
        transactionService.deleteTransaction(id_trans);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.findTransactionById(id);
        return transaction.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/byYear/{year}")
    public ResponseEntity<List<Transaction>> getTransactionsByYear(@PathVariable int year) {
        List<Transaction> transactions = transactionService.findTransactionsByYear(year);
        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/date/{year}/{month}")
    public ResponseEntity<List<Transaction>> findTransactionByYearAndMonth(@PathVariable Integer year, @PathVariable Integer month) {
        List<Transaction> transactions = transactionService.findTransactionByYearAndMonth(year, month);
        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/date/{year}/{month}/{day}")
    public ResponseEntity<List<Transaction>> findTransactionByYearAndMonthAndDay(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        List<Transaction> transactions = transactionService.findTransactionByYearAndMonthAndDay(year, month, day);
        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }


    @GetMapping("/nip/{nip}")
    public ResponseEntity<List<Transaction>> findTransactionsByNip(@PathVariable("nip") Long nip) {
        List<Transaction> transactions = transactionService.findTransactionsByNip(nip);
        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }


}

/////////////////dump////////////////////////
//    @PostMapping("/create") ##sama saja, tp di bodynya nanti pakai employee
//    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
//        TransactionDTO createdTransaction = transactionService.createTransaction(transactionDTO);
//        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<List<TransactionDTO>> updatetransaction(@PathVariable("id") Long id_trans, @RequestBody TransactionDTO transactionDTO){
//        transactionDTO.setId_trans(id_trans);
//        TransactionDTO  updatetransaction = transactionService.updatetransaction(transactionDTO);
//        return  new ResponseEntity<>(updatetransaction,HttpStatus.OK);
//    }

//    @GetMapping("/day/{day}")
//    public ResponseEntity<List<TransactionDTO>> findTransactionsByDay(@PathVariable Integer day) {
//        List<TransactionDTO> transactions = transactionService.findTransactionsByDay(day);
//        if (transactions.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(transactions);
//
//    }
//
//    @GetMapping("/month/{month}")
//    public ResponseEntity<List<TransactionDTO>> findTransactionsByMonth(@PathVariable Integer month) {
//        List<TransactionDTO> transactions = transactionService.findTransactionsByMonth(month);
//        if (transactions.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(transactions);
//    }
//    @GetMapping("/nip/{nip}")
//    public ResponseEntity<List<TransactionDTO>> findTransactionsByNip(@PathVariable Long nip) {
//        List<TransactionDTO> transactions = transactionService.findTransactionsByNip(nip);
//        if (transactions.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(transactions);
//    }
