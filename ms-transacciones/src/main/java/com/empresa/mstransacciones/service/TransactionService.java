package com.empresa.mstransacciones.service;

import com.empresa.mstransacciones.dto.TransactionResponseDTO;
import com.empresa.mstransacciones.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<TransactionResponseDTO> getAllTransactions();
    Transaction createTransaction(Transaction transaction);
    Transaction updateTransaction(Long id, Transaction transaction);
    void deleteTransaction(Long id);
    void procesarTransaccion(Long clienteId, Long usuarioId);
}
