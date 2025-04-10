package com.empresa.msclientes.seed;

import com.empresa.msclientes.model.Cliente;
import com.empresa.msclientes.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClienteSeeder implements CommandLineRunner {

    private final ClienteRepository clienteRepository;

    public ClienteSeeder(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (clienteRepository.count() == 0) {
            for (int i = 1; i <= 10; i++) {
                Cliente cliente = new Cliente();
                cliente.setNombre("Cliente " + i);
                cliente.setCorreo("cliente" + i + "@correo.com");
                cliente.setDireccion("Dirección " + i);
                clienteRepository.save(cliente);
            }
            System.out.println("✅ 10 clientes ficticios creados.");
        }
    }
}

