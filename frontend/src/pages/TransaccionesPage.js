// src/pages/TransaccionesPage.js
import React, { useState } from 'react';
import TransaccionList from '../components/TransaccionList';
import TransaccionForm from '../components/TransaccionForm';

const TransaccionesPage = () => {
    const [isEditing, setIsEditing] = useState(false);
    const [selectedTransaccion, setSelectedTransaccion] = useState(null);

    const handleEdit = (transaccion) => {
        setIsEditing(true);
        setSelectedTransaccion(transaccion);
    };

    const handleSave = () => {
        setIsEditing(false);
        setSelectedTransaccion(null);
    };

    return (
        <div>
            <h1>Transacciones</h1>
            {isEditing ? (
                <TransaccionForm transaccion={selectedTransaccion} onSave={handleSave} />
            ) : (
                <TransaccionList onEdit={handleEdit} />
            )}
        </div>
    );
};

export default TransaccionesPage;
