import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Sidebar from "./components/Sidebar";
import ConvertPayment from "./pages/ConvertPayment";
import { ToastContainer } from "react-toastify";
import SavePayment from "./pages/SavePayment";
import SuccessPayments from "./pages/SuccessPayments";
import FailedPayments from "./pages/FailedPayments";

function App() {
  return (
    <Router>
      <div className="app-container">
        <Sidebar />
        <div className="content">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/convert-payment" element={<ConvertPayment />} />
            <Route path="/save-payment" element={< SavePayment/>} /> 
            <Route path="/successful-payments" element={< SuccessPayments/>} /> 
            <Route path="/failed-payments" element={< FailedPayments/>} /> 
              
          </Routes>
        </div>
        <ToastContainer position="top-right" autoClose={3000} />
      </div>
    </Router>
  );
}

export default App;
