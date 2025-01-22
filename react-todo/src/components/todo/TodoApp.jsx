import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import AuthProvider, { useAuth } from './security/AuthContext';
import WelcomeComponent from './WelcomeComponent';
import ListTodosComponent from './ListTodosComponent';
import LogoutComponent from './LogoutComponent';
import LoginComponent from './LoginComponent';
import HeaderComponent from './HeaderComponent';
import FooterComponent from './FooterComponent';
import './TodoApp.css'; 

function AuthenticatedRoute({ children }) {
    const authContext = useAuth();
    if (authContext.isAuthenticated) {
        return children;
    }
    return <Navigate to="/login" />;
}

function TodoApp() {
    return (
        <AuthProvider>
            <BrowserRouter>
                <div className="d-flex flex-column vh-100">
                    {/* Header */}
                    <HeaderComponent />

                    {/* Main Content */}
                    <main className="flex-grow-1 container my-4">
                        <Routes>
                            <Route path="/" element={<LoginComponent />} />
                            <Route path="/login" element={<LoginComponent />} />
                            <Route
                                path="/welcome/:username"
                                element={
                                    <AuthenticatedRoute>
                                        <WelcomeComponent />
                                    </AuthenticatedRoute>
                                }
                            />
                            <Route
                                path="/todos"
                                element={
                                    <AuthenticatedRoute>
                                        <ListTodosComponent />
                                    </AuthenticatedRoute>
                                }
                            />
                            <Route
                                path="/logout"
                                element={
                                    <AuthenticatedRoute>
                                        <LogoutComponent />
                                    </AuthenticatedRoute>
                                }
                            />
                            <Route path="*" element={<Navigate to="/" />} />
                        </Routes>
                    </main>

                    {/* Footer */}
                    <FooterComponent />
                </div>
            </BrowserRouter>
        </AuthProvider>
    );
}

export default TodoApp;

