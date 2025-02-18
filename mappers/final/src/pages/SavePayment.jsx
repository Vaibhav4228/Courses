import React, { useState, useEffect } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Typography,
  Button,
  TextField,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  CircularProgress,
} from "@mui/material";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import axios from "axios";
import axiosInstance from "../utils/axiosInstance";
import "../styles/SavePayment.css";

const SavePayment = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [paymentData, setPaymentData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const paymentsPerPage = 20;

  const [editInvoice, setEditInvoice] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    if (location.state && location.state.convertedData) {
      setPaymentData(location.state.convertedData);
    } else {
      toast.error("No converted payment data found.");
    }
  }, [location.state]);

  const handleInputChange = (event, section, key) => {
    setPaymentData((prev) => ({
      ...prev,
      [section]: {
        ...prev[section],
        [key]: event.target.value,
      },
    }));
  };

  const handleInvoiceEdit = (invoice, index) => {
    setEditInvoice({ ...invoice, index });
    setIsModalOpen(true);
  };

  const handleInvoiceChange = (event, key) => {
    setEditInvoice((prev) => ({
      ...prev,
      [key]: event.target.value,
    }));
  };

  const saveInvoiceChanges = () => {
    const updatedInvoices = [...paymentData.invoices];
    updatedInvoices[editInvoice.index] = {
      invoice_type: editInvoice.invoice_type,
      invoice_date: editInvoice.invoice_date,
      invoice_amount: editInvoice.invoice_amount,
    };
    setPaymentData((prev) => ({
      ...prev,
      invoices: updatedInvoices,
    }));
    setIsModalOpen(false);
  };

  const validatePayment = (data) => {
    if (!data.paymentHeaders.paymentName || !data.paymentHeaders.pay_id || !data.paymentHeaders.pay_type) {
      return "Payment name, ID, and type are required.";
    }
    if (!data.paymentReqDetails.paymentReceiverName || !data.paymentReqDetails.companyCode || !data.paymentReqDetails.plant) {
      return "Receiver name, company code, and plant are required.";
    }
    if (!data.paymentReqDetails.amount || isNaN(parseFloat(data.paymentReqDetails.amount))) {
      return "Valid amount is required.";
    }
    if (data.paymentReqDetails.pay_type === "BANK_TRANSACTION" && parseFloat(data.paymentReqDetails.amount) < 10000) {
      return "BANK_TRANSACTION payments must be at least 10,000.";
    }
    if (!data.invoices || data.invoices.length === 0) {
      return "At least one invoice is required.";
    }
    for (let invoice of data.invoices) {
      if (!invoice.invoice_type || !invoice.invoice_date || !invoice.invoice_amount) {
        return "Each invoice must have a type, date, and amount.";
      }
    }
    return null;
  };

  const handleSavePayment = async () => {
    if (!paymentData) {
      toast.error("No payment data available to save.");
      return;
    }

    const validationError = validatePayment(paymentData);
    if (validationError) {
      toast.error(validationError);
      return;
    }

    try {
      setLoading(true);
      await axiosInstance.post("http://localhost:9500/payment/Saving-DB", paymentData); 
      toast.success("Payment saved successfully!");
      navigate("/successful-payments");
    } catch (error) {
      toast.error("Error saving payment. Redirecting to failed payments.");
      navigate("/failed-payments");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="save-container">
      <Typography variant="h4" align="center" gutterBottom>
        Review & Edit Payment
      </Typography>

      {paymentData && (
        <>
          <div className="edit-section">
            <TextField label="Payment Name" fullWidth value={paymentData.paymentHeaders.paymentName} onChange={(e) => handleInputChange(e, "paymentHeaders", "paymentName")} />
            <TextField label="Pay ID" fullWidth value={paymentData.paymentHeaders.pay_id} onChange={(e) => handleInputChange(e, "paymentHeaders", "pay_id")} />
            <TextField label="Pay Type" fullWidth value={paymentData.paymentHeaders.pay_type} onChange={(e) => handleInputChange(e, "paymentHeaders", "pay_type")} />
            <TextField label="Receiver Name" fullWidth value={paymentData.paymentReqDetails.paymentReceiverName} onChange={(e) => handleInputChange(e, "paymentReqDetails", "paymentReceiverName")} />
            <TextField label="Amount" fullWidth value={paymentData.paymentReqDetails.amount} onChange={(e) => handleInputChange(e, "paymentReqDetails", "amount")} />
            <TextField label="Company Code" fullWidth value={paymentData.paymentReqDetails.companyCode} onChange={(e) => handleInputChange(e, "paymentReqDetails", "companyCode")} />
            <TextField label="Transaction Code" fullWidth value={paymentData.paymentReqDetails.transactionCode} onChange={(e) => handleInputChange(e, "paymentReqDetails", "transactionCode")} />
            <TextField label="Plant" fullWidth value={paymentData.paymentReqDetails.plant} onChange={(e) => handleInputChange(e, "paymentReqDetails", "plant")} />
            <TextField label="GST" fullWidth value={paymentData.paymentReqDetails.gst} onChange={(e) => handleInputChange(e, "paymentReqDetails", "gst")} />
          </div>

          <TableContainer component={Paper} className="table-container">
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Invoice Type</TableCell>
                  <TableCell>Invoice Date</TableCell>
                  <TableCell>Invoice Amount</TableCell>
                  <TableCell>Action</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {paymentData.invoices.map((invoice, index) => (
                  <TableRow key={index}>
                    <TableCell>{invoice.invoice_type}</TableCell>
                    <TableCell>{invoice.invoice_date}</TableCell>
                    <TableCell>{invoice.invoice_amount}</TableCell>
                    <TableCell>
                      <Button variant="contained" onClick={() => handleInvoiceEdit(invoice, index)}>
                        Edit
                      </Button>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>

          <Button variant="contained" color="primary" onClick={handleSavePayment} disabled={loading} className="save-btn">
            {loading ? <CircularProgress size={24} /> : "Save Payment"}
          </Button>
        </>
      )}

      <Dialog open={isModalOpen} onClose={() => setIsModalOpen(false)}>
  <DialogTitle>Edit Invoice</DialogTitle>
  <DialogContent className="modal-content">
    <TextField label="Invoice Type" fullWidth value={editInvoice?.invoice_type} onChange={(e) => handleInvoiceChange(e, "invoice_type")} />
    <TextField label="Invoice Date" fullWidth value={editInvoice?.invoice_date} onChange={(e) => handleInvoiceChange(e, "invoice_date")} />
    <TextField label="Invoice Amount" fullWidth value={editInvoice?.invoice_amount} onChange={(e) => handleInvoiceChange(e, "invoice_amount")} />
  </DialogContent>
  <DialogActions className="modal-buttons">
    <Button onClick={() => setIsModalOpen(false)}>Cancel</Button>
    <Button variant="contained" color="primary" onClick={saveInvoiceChanges}>Save</Button>
  </DialogActions>
</Dialog>
    </div>
  );
};

export default SavePayment;
