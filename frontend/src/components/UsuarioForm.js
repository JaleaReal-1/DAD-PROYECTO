// src/components/UsuarioForm.js
import React, { useState } from 'react';
import { createUsuario, updateUsuario } from '../services/api';

const UsuarioForm = ({ usuario, onSave }) => {
    const [name, setName] = useState(usuario ? usuario.name : '');

    const handleSubmit = async (event) => {
        event.preventDefault();
        const newUsuario = { name };
        if (usuario) {
            await updateUsuario(usuario.id, newUsuario);
        } else {
            await createUsuario(newUsuario);
        }
        onSave();
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Nombre del usuario"
            />
            <button type="submit">Guardar</button>
        </form>
    );
};

export default UsuarioForm;
