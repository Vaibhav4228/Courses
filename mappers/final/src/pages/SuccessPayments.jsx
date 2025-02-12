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
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Pagination,
  CircularProgress,
} from "@mui/material";
import { toast } from "react-toastify";
import axios from "axios";
import { motion } from "framer-motion";
import Typewriter from "typewriter-effect";
import "../styles/SuccessPayments.css";

const SuccessPayments = () => {
  const [payments, setPayments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const paymentsPerPage = 20;
  const [selectedInvoices, setSelectedInvoices] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchPayments = async () => {
      try {
        const response = await axios.get("http://localhost:9500/payment/Successfull-Payment");
        setPayments(response.data);
        setLoading(false);
      } catch (error) {
        toast.error("Error fetching successful payments.");
        setLoading(false);
      }
    };

    fetchPayments();
  }, []);

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:9500/payment/delete/${id}`);
      setPayments((prev) => prev.filter((payment) => payment.id !== id));
      toast.success("Payment deleted successfully!");
    } catch (error) {
      toast.error("Error deleting payment.");
    }
  };

  const handleViewInvoices = (invoices) => {
    setSelectedInvoices(invoices);
    setIsModalOpen(true);
  };

  const lastPage = currentPage * paymentsPerPage;
  const firstPage = lastPage - paymentsPerPage;
  const currentPayments = payments.slice(firstPage, lastPage);

  return (
    <div className="success-container">
      <motion.div
        initial={{ opacity: 0, y: -30 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 1 }}
        className="title-animation"
      >
        <Typography variant="h4" align="center" gutterBottom>
          <Typewriter
            options={{
              strings: ["Successful Transactions"],
              autoStart: true,
              loop: true,
            }}
          />
        </Typography>
      </motion.div>

      {loading ? (
        <CircularProgress className="loading-spinner" />
      ) : (
        <TableContainer component={Paper} className="table-container">
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell>Payment Name</TableCell>
                <TableCell>Pay ID</TableCell>
                <TableCell>Pay Type</TableCell>
                <TableCell>Receiver Name</TableCell>
                <TableCell>Amount</TableCell>
                <TableCell>Company Code</TableCell>
                <TableCell>Transaction Code</TableCell>
                <TableCell>Plant</TableCell>
                <TableCell>GST</TableCell>
                <TableCell>Invoices</TableCell>
                <TableCell>Action</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {currentPayments.length > 0 ? (
                currentPayments.map((payment) => (
                  <TableRow key={payment.id}>
                    <TableCell>{payment.id}</TableCell>
                    <TableCell>{payment.paymentName}</TableCell>
                    <TableCell>{payment.payId}</TableCell>
                    <TableCell>{payment.payType}</TableCell>
                    <TableCell>{payment.paymentReceiverName}</TableCell>
                    <TableCell>{payment.amount}</TableCell>
                    <TableCell>{payment.companyCode}</TableCell>
                    <TableCell>{payment.transactionCode}</TableCell>
                    <TableCell>{payment.plant}</TableCell>
                    <TableCell>{payment.gst}</TableCell>
                    <TableCell>
                      <Button variant="contained" onClick={() => handleViewInvoices(payment.invoices)}>
                        View
                      </Button>
                    </TableCell>
                    <TableCell>
                      <Button variant="contained" color="error" onClick={() => handleDelete(payment.id)}>
                        Delete
                      </Button>
                    </TableCell>
                  </TableRow>
                ))
              ) : (
                <TableRow>
                  <TableCell colSpan={12} align="center">
                    No successful payments found.
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>
      )}

      <Pagination
        count={Math.ceil(payments.length / paymentsPerPage)}
        page={currentPage}
        onChange={(event, value) => setCurrentPage(value)}
        className="pagination"
      />

      
<Dialog open={isModalOpen} onClose={() => setIsModalOpen(false)}>
  <DialogTitle>Invoice Details</DialogTitle>
  <DialogContent className="modal-content">
    <Table>
      <TableHead>
        <TableRow>
          <TableCell>Invoice Type</TableCell>
          <TableCell>Invoice Date</TableCell>
          <TableCell>Invoice Amount</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {selectedInvoices.length > 0 ? (
          selectedInvoices.map((invoice, index) => (
            <TableRow key={index}>
              <TableCell>{invoice.invoiceType}</TableCell>
              <TableCell>
                {new Intl.DateTimeFormat("en-GB", {
                  year: "numeric",
                  month: "2-digit",
                  day: "2-digit",
                }).format(new Date(invoice.invoiceDate))}
              </TableCell>
              <TableCell>{invoice.invoiceAmount}</TableCell>
            </TableRow>
          ))
        ) : (
          <TableRow>
            <TableCell colSpan={3} align="center">
              No invoices available.
            </TableCell>
          </TableRow>
        )}
      </TableBody>
    </Table>
  </DialogContent>
  <DialogActions>
    <Button onClick={() => setIsModalOpen(false)}>Close</Button>
  </DialogActions>
</Dialog>

    </div>
  );
};

export default SuccessPayments;
