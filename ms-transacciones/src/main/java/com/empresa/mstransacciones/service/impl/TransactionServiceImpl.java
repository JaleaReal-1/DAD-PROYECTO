package com.empresa.mstransacciones.service.impl;

import com.empresa.mstransacciones.dto.ClienteDTO;
import com.empresa.mstransacciones.dto.TransactionResponseDTO;
import com.empresa.mstransacciones.dto.UsuarioDTO;
import com.empresa.mstransacciones.feign.ClienteFeign;
import com.empresa.mstransacciones.feign.UsuarioFeign;
import com.empresa.mstransacciones.model.Transaction;
import com.empresa.mstransacciones.repository.TransactionRepository;
import com.empresa.mstransacciones.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClienteFeign clienteFeign;

    @Autowired
    private UsuarioFeign usuarioFeign;

    @Override
    public List<TransactionResponseDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream().map(transaction -> {
            TransactionResponseDTO dto = new TransactionResponseDTO();
            dto.setId(transaction.getId());
            dto.setDescription(transaction.getDescription());
            dto.setAmount(transaction.getAmount());
            dto.setClienteId(transaction.getClienteId());
            dto.setUsuarioId(transaction.getUsuarioId());

            try {
                ClienteDTO cliente = clienteFeign.obtenerClientePorId(transaction.getClienteId());
                dto.setClienteNombre(cliente.getNombre());
            } catch (Exception e) {
                dto.setClienteNombre("Cliente no encontrado");
            }

            try {
                UsuarioDTO usuario = usuarioFeign.obtenerUsuarioPorId(transaction.getUsuarioId());
                dto.setUsuarioNombre(usuario.getUsername());
            } catch (Exception e) {
                dto.setUsuarioNombre("Usuario no encontrado");
            }

            return dto;
        }).toList();
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction transaction) {
        Transaction existing = transactionRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setAmount(transaction.getAmount());
            existing.setDescription(transaction.getDescription());
            existing.setClienteId(transaction.getClienteId());
            existing.setUsuarioId(transaction.getUsuarioId());
            return transactionRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public void procesarTransaccion(Long clienteId, Long usuarioId) {
        ClienteDTO cliente = clienteFeign.obtenerClientePorId(clienteId);
        UsuarioDTO usuario = usuarioFeign.obtenerUsuarioPorId(usuarioId);

        System.out.println("Cliente recibido desde ms-clientes: " + cliente.getNombre());
        System.out.println("Usuario recibido desde ms-usuarios: " + usuario.getUsername());
    }
}
