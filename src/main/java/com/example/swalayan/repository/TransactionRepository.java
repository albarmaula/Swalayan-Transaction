package com.example.swalayan.repository;

import com.example.swalayan.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends CrudRepository <Transaction, Long> {
    
    List<Transaction> findByDay(int day);

    List<Transaction> findByMonth(int month);

    List<Transaction> findByYear(int year);

    List<Transaction> findByNip(long nip);

    @Query("from Transaction t where t.year = ?1 or t.month = ?2 or t.day = ?3")
    List<Transaction> findByYearOrMonthOrDay(int year, int month, int day);
}
