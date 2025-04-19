// src/components/ClienteList.js
import React, { useEffect, useState } from 'react';

const ClienteList = ({ onEdit }) => {
    const [clientes, setClientes] = useState([]);

    useEffect(() => {
        // Aquí puedes hacer una solicitud a tu API para obtener los clientes
        // Simularemos los datos
        setClientes([
            { id: 1, nombre: 'Juan Pérez', email: 'juan@example.com' },
            { id: 2, nombre: 'Ana Gómez', email: 'ana@example.com' }
        ]);
    }, []);

    return (
        <div>
            <h2>Lista de Clientes</h2>
            <ul>
                {clientes.map(cliente => (
                    <li key={cliente.id}>
                        <span>{cliente.nombre} - {cliente.email}</span>
                        <button onClick={() => onEdit(cliente)}>Editar</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ClienteList;
