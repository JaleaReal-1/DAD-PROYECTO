import axios from 'axios';

// URL del API Gateway (modifica si es necesario)
const API_URL = 'http://localhost:8085';

export const api = axios.create({
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

// Función para obtener todos los usuarios
export const getUsuarios = async () => {
    try {
        const response = await api.get('/usuarios');
        return response.data;
    } catch (error) {
        console.error('Error al obtener los usuarios', error);
        throw error;
    }
};

// Función para obtener todos los clientes
export const getClientes = async () => {
    try {
        const response = await api.get('/clientes');
        return response.data;
    } catch (error) {
        console.error('Error al obtener los clientes', error);
        throw error;
    }
};

// Función para obtener todas las transacciones
export const getTransacciones = async () => {
    try {
        const response = await api.get('/transactions');
        return response.data;
    } catch (error) {
        console.error('Error al obtener las transacciones', error);
        throw error;
    }
};

// Funciones para CRUD de usuarios
export const createUsuario = async (usuario) => {
    try {
        const response = await api.post('/usuarios', usuario);
        return response.data;
    } catch (error) {
        console.error('Error al crear el usuario', error);
        throw error;
    }
};

export const updateUsuario = async (id, usuario) => {
    try {
        const response = await api.put(`/usuarios/${id}`, usuario);
        return response.data;
    } catch (error) {
        console.error('Error al actualizar el usuario', error);
        throw error;
    }
};

export const deleteUsuario = async (id) => {
    try {
        await api.delete(`/usuarios/${id}`);
    } catch (error) {
        console.error('Error al eliminar el usuario', error);
        throw error;
    }
};
