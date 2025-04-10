package com.empresa.mstransacciones.repository;

import com.empresa.mstransacciones.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}