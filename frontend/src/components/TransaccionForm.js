// src/components/TransaccionForm.js
import React, { useState, useEffect } from 'react';

const TransaccionForm = ({ transaccion, onSave }) => {
    const [monto, setMonto] = useState('');
    const [cliente, setCliente] = useState('');
    const [fecha, setFecha] = useState('');

    useEffect(() => {
        if (transaccion) {
            setMonto(transaccion.monto);
            setCliente(transaccion.cliente);
            setFecha(transaccion.fecha);
        }
    }, [transaccion]);

    const handleSubmit = (e) => {
        e.preventDefault();
        // Aquí puedes hacer la lógica para guardar la transacción
        // Simularemos el guardado con un console.log
        console.log({ monto, cliente, fecha });
        onSave();  // Llamamos al método onSave para regresar a la vista de la lista
    };

    return (
        <div>
            <h2>{transaccion ? 'Editar Transacción' : 'Nueva Transacción'}</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Monto:</label>
                    <input
                        type="number"
                        value={monto}
                        onChange={(e) => setMonto(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Cliente:</label>
                    <input
                        type="text"
                        value={cliente}
                        onChange={(e) => setCliente(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Fecha:</label>
                    <input
                        type="date"
                        value={fecha}
                        onChange={(e) => setFecha(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">{transaccion ? 'Actualizar' : 'Guardar'}</button>
            </form>
        </div>
    );
};

export default TransaccionForm;

