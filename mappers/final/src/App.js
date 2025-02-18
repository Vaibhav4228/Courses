import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import Home from "./pages/Home";
import Sidebar from "./components/Sidebar";
import ConvertPayment from "./pages/ConvertPayment";
import SavePayment from "./pages/SavePayment";
import SuccessPayments from "./pages/SuccessPayments";
import FailedPayments from "./pages/FailedPayments";
import Signup from "./auth/Signup";
import Login from "./auth/Login";
import Logout from "./auth/Logout";
import { AuthProvider, useAuth } from "./auth/AuthContext";
import ProtectedRoute from "./auth/ProtectedRoute";
import MakePayment from "./pages/MakePayment";

function App() {
  return (
    <Router>
      <AuthProvider>
        <MainApp />
      </AuthProvider>
      <ToastContainer position="top-right" autoClose={3000} />
    </Router>
  );
}

function MainApp() {
  const { user } = useAuth();

  return (
    <div className="app-container">
      {user && <Sidebar />}
      <div className="content">
        <Routes>
          <Route path="/signup" element={<Signup />} />
          <Route path="/login" element={<Login />} />
          <Route path="/logout" element={<Logout />} />

          <Route path="/" element={<ProtectedRoute><Home /></ProtectedRoute>} />
          <Route path="/convert-payment" element={<ProtectedRoute><ConvertPayment /></ProtectedRoute>} />
          <Route path="/save-payment" element={<ProtectedRoute><SavePayment /></ProtectedRoute>} />
          <Route path="/successful-payments" element={<ProtectedRoute><SuccessPayments /></ProtectedRoute>} />
          <Route path="/failed-payments" element={<ProtectedRoute><FailedPayments />
          </ProtectedRoute>} />
          <Route path="/make-payment" element={<ProtectedRoute><MakePayment /></ProtectedRoute>} />
  
        </Routes>
      </div>
    </div>
  );
}

export default App;
