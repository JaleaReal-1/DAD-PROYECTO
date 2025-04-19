// src/pages/UsuariosPage.js
import React, { useState } from 'react';
import UsuarioList from '../components/UsuarioList';
import UsuarioForm from '../components/UsuarioForm';

const UsuariosPage = () => {
    const [isEditing, setIsEditing] = useState(false);
    const [selectedUsuario, setSelectedUsuario] = useState(null);

    const handleEdit = (usuario) => {
        setIsEditing(true);
        setSelectedUsuario(usuario);
    };

    const handleSave = () => {
        setIsEditing(false);
        setSelectedUsuario(null);
    };

    return (
        <div>
            <h1>Usuarios</h1>
            {isEditing ? (
                <UsuarioForm usuario={selectedUsuario} onSave={handleSave} />
            ) : (
                <UsuarioList onEdit={handleEdit} />
            )}
        </div>
    );
};

export default UsuariosPage;
