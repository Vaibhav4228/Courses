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
import "../styles/SuccessPayments.css";

const SuccessPayments = () => {
  const [payments, setPayments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const paymentsPerPage = 20;
  const [selectedInvoices, setSelectedInvoices] = useState([]);
  const [invoicePage, setInvoicePage] = useState(1);
  const invoicesPerPage = 5;
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [grandTotal, setGrandTotal] = useState(null);
  const [currentPayloadId, setCurrentPayloadId] = useState(null);
  const [showGrandTotal, setShowGrandTotal] = useState(false);

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

  const handleViewInvoices = (invoices, payloadId) => {
    setSelectedInvoices(invoices);
    setCurrentPayloadId(payloadId);
    setGrandTotal(null);
    setShowGrandTotal(false);
    setInvoicePage(1);
    setIsModalOpen(true);
  };

  const handleCalculateGrandTotal = async () => {
    if (!currentPayloadId) {
      toast.error("No Payload ID found!");
      return;
    }

    try {
      await axios.post(`http://localhost:9500/batch/calculate-grand-total/${currentPayloadId}`);
      toast.success("Batch job started!");

      setTimeout(async () => {
        const totalResponse = await axios.get(`http://localhost:9500/batch/latest-grand-total/${currentPayloadId}`);
        setGrandTotal(parseFloat(totalResponse.data).toFixed(3));
      }, 3000);
    } catch (error) {
      toast.error("Error starting batch job.");
    }
  };

  const handleShowGrandTotal = async () => {
    if (!currentPayloadId) {
      toast.error("No Payload ID found!");
      return;
    }

    try {
      const response = await axios.get(`http://localhost:9500/batch/latest-grand-total/${currentPayloadId}`);
      setGrandTotal(parseFloat(response.data).toFixed(3));
      setShowGrandTotal(true);
    } catch (error) {
      toast.error("Error fetching grand total.");
    }
  };

  const lastPaymentIndex = currentPage * paymentsPerPage;
  const firstPaymentIndex = lastPaymentIndex - paymentsPerPage;
  const currentPayments = payments.slice(firstPaymentIndex, lastPaymentIndex);

  const lastInvoiceIndex = invoicePage * invoicesPerPage;
  const firstInvoiceIndex = lastInvoiceIndex - invoicesPerPage;
  const currentInvoices = selectedInvoices.slice(firstInvoiceIndex, lastInvoiceIndex);

  return (
    <div className="success-container">
      <Typography variant="h4" align="center" gutterBottom>
        Successful Transactions
      </Typography>

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
              {currentPayments.map((payment) => (
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
                    <Button
                      variant="contained"
                      onClick={() => handleViewInvoices(payment.invoices, payment.id)}
                    >
                      View
                    </Button>
                  </TableCell>
                  <TableCell>
                    <Button variant="contained" color="error" onClick={() => handleDelete(payment.id)}>
                      Delete
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
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
              {currentInvoices.map((invoice, index) => (
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
              ))}
            </TableBody>
          </Table>
          <Pagination
            count={Math.ceil(selectedInvoices.length / invoicesPerPage)}
            page={invoicePage}
            onChange={(event, value) => setInvoicePage(value)}
            className="pagination"
          />
          <Button
            variant="contained"
            color="primary"
            onClick={handleCalculateGrandTotal}
            style={{ marginTop: "10px", width: "100%" }}
          >
            Calculate Grand Total
          </Button>
        </DialogContent>
        <DialogActions>
          {!showGrandTotal ? (
            <Button variant="contained" color="success" onClick={handleShowGrandTotal}>
              See Grand Total
            </Button>
          ) : (
            <Typography variant="h6" style={{ padding: "10px" }}>
              Grand Total: ₹{grandTotal}
            </Typography>
          )}
          <Button onClick={() => setIsModalOpen(false)}>Close</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default SuccessPayments;
