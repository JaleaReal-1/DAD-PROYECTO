// src/components/TransaccionList.js
import React, { useEffect, useState } from 'react';

const TransaccionList = ({ onEdit }) => {
    const [transacciones, setTransacciones] = useState([]);

    useEffect(() => {
        // Aquí puedes hacer una solicitud a tu API para obtener las transacciones
        // Simularemos los datos
        setTransacciones([
            { id: 1, monto: 1000, cliente: 'Juan Pérez', fecha: '2023-05-12' },
            { id: 2, monto: 500, cliente: 'Ana Gómez', fecha: '2023-06-14' }
        ]);
    }, []);

    return (
        <div>
            <h2>Lista de Transacciones</h2>
            <ul>
                {transacciones.map(transaccion => (
                    <li key={transaccion.id}>
                        <span>{transaccion.cliente} - ${transaccion.monto} - {transaccion.fecha}</span>
                        <button onClick={() => onEdit(transaccion)}>Editar</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default TransaccionList;

