// src/components/UsuarioList.js
import React, { useEffect, useState } from 'react';
import { getUsuarios, deleteUsuario } from '../services/api';

const UsuarioList = () => {
    const [usuarios, setUsuarios] = useState([]);

    useEffect(() => {
        const fetchUsuarios = async () => {
            const data = await getUsuarios();
            setUsuarios(data);
        };

        fetchUsuarios();
    }, []);

    const handleDelete = async (id) => {
        await deleteUsuario(id);
        setUsuarios(usuarios.filter(usuario => usuario.id !== id));
    };

    return (
        <div>
            <h2>Usuarios</h2>
            <ul>
                {usuarios.map(usuario => (
                    <li key={usuario.id}>
                        {usuario.name}
                        <button onClick={() => handleDelete(usuario.id)}>Eliminar</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default UsuarioList;
