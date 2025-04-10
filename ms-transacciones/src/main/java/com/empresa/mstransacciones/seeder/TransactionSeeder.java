package com.empresa.mstransacciones.seeder;

import com.empresa.mstransacciones.model.Transaction;
import com.empresa.mstransacciones.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TransactionSeeder implements CommandLineRunner {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verifica si ya existen datos en la base de datos
        if (transactionRepository.count() == 0) {
            // Si no existen datos, insertar 10 registros ficticios
            for (int i = 1; i <= 10; i++) {
                Transaction transaction = new Transaction();
                transaction.setDescription("Transaction " + i);
                transaction.setAmount(Math.random() * 1000); // Genera un valor aleatorio entre 0 y 1000
                transactionRepository.save(transaction);
            }
            System.out.println("10 transacciones de prueba insertadas.");
        } else {
            System.out.println("Las transacciones ya están insertadas.");
        }
    }
}
