// src/pages/ClientesPage.js
import React, { useState } from 'react';
import ClienteList from '../components/ClienteList';
import ClienteForm from '../components/ClienteForm';

const ClientesPage = () => {
    const [isEditing, setIsEditing] = useState(false);
    const [selectedCliente, setSelectedCliente] = useState(null);

    const handleEdit = (cliente) => {
        setIsEditing(true);
        setSelectedCliente(cliente);
    };

    const handleSave = () => {
        setIsEditing(false);
        setSelectedCliente(null);
    };

    return (
        <div>
            <h1>Clientes</h1>
            {isEditing ? (
                <ClienteForm cliente={selectedCliente} onSave={handleSave} />
            ) : (
                <ClienteList onEdit={handleEdit} />
            )}
        </div>
    );
};

export default ClientesPage;
