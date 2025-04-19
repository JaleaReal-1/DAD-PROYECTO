import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import ClientesPage from './pages/ClientesPage';
import TransaccionesPage from './pages/TransaccionesPage';
import UsuariosPage from './pages/UsuariosPage';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Navigate to="/usuarios" />} /> {/* Redirige a la página de usuarios */}
                <Route path="/clientes" element={<ClientesPage />} />
                <Route path="/transacciones" element={<TransaccionesPage />} />
                <Route path="/usuarios" element={<UsuariosPage />} />
            </Routes>
        </Router>
    );
}

export default App;
