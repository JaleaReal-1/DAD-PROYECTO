// src/components/ClienteForm.js
import React, { useState, useEffect } from 'react';

const ClienteForm = ({ cliente, onSave }) => {
    const [nombre, setNombre] = useState('');
    const [email, setEmail] = useState('');

    useEffect(() => {
        if (cliente) {
            setNombre(cliente.nombre);
            setEmail(cliente.email);
        }
    }, [cliente]);

    const handleSubmit = (e) => {
        e.preventDefault();
        // Aquí puedes hacer la lógica para guardar el cliente
        // Simularemos el guardado con un console.log
        console.log({ nombre, email });
        onSave();  // Llamamos al método onSave para regresar a la vista de la lista
    };

    return (
        <div>
            <h2>{cliente ? 'Editar Cliente' : 'Nuevo Cliente'}</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Nombre:</label>
                    <input
                        type="text"
                        value={nombre}
                        onChange={(e) => setNombre(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">{cliente ? 'Actualizar' : 'Guardar'}</button>
            </form>
        </div>
    );
};

export default ClienteForm;
