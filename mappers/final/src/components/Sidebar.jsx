import React, { useState } from "react";
import { Link } from "react-router-dom";
import { motion } from "framer-motion";
import {
  Menu,
  Close,
  Home,
  Receipt,
  Save,
  CheckCircle,
  Cancel,
  Logout,
  Payment 
} from "@mui/icons-material";
import { Button } from "@mui/material";
import "../styles/Sidebar.css";
import { useAuth } from "../auth/AuthContext";

const Sidebar = () => {
  const [isOpen, setIsOpen] = useState(false);
  const { logout } = useAuth();

  return (
    <motion.div
      className="sidebar"
      animate={{ width: isOpen ? "250px" : "60px" }}
      transition={{ duration: 0.3 }}
    >
      <div className="menu-icon" onClick={() => setIsOpen(!isOpen)}>
        {isOpen ? <Close /> : <Menu />}
      </div>

      <nav className="nav-links">
        <Link to="/" className="nav-item">
          <Home className="nav-icon" />
          {isOpen && <span>Home</span>}
        </Link>

        <Link to="/convert-payment" className="nav-item">
          <Receipt className="nav-icon" />
          {isOpen && <span>Convert Payment</span>}
        </Link>

        <Link to="/save-payment" className="nav-item">
          <Save className="nav-icon" />
          {isOpen && <span>Save Payment</span>}
        </Link>

        <Link to="/successful-payments" className="nav-item">
          <CheckCircle className="nav-icon" />
          {isOpen && <span>Successful</span>}
        </Link>

        <Link to="/failed-payments" className="nav-item">
          <Cancel className="nav-icon" />
          {isOpen && <span>Failed</span>}
        </Link>
        <Link to="/make-payment" className="nav-item">
          <Payment className="nav-icon" />
          {isOpen && <span>Make Payment</span>}
        </Link>
      </nav>

      <div className="sidebar-footer">
        <Button
          variant="contained"
          color="error"
          onClick={logout}
          className="logout-button"
        >
          {isOpen ? "Logout" : <Logout />}
        </Button>
      </div>
    </motion.div>
  );
};

export default Sidebar;
