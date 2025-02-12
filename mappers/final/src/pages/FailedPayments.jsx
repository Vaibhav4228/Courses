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
  Pagination,
  CircularProgress,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions
} from "@mui/material";
import { toast } from "react-toastify";
import axios from "axios";
import "../styles/FailedPayments.css";

const FailedPayments = () => {
  const [failedPayments, setFailedPayments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const paymentsPerPage = 20;
  const [selectedRawData, setSelectedRawData] = useState(null);
  const [isDialogOpen, setIsDialogOpen] = useState(false);

  useEffect(() => {
    const fetchFailedPayments = async () => {
      try {
        const response = await axios.get("http://localhost:9500/payment/Failed-Payments");
        setFailedPayments(response.data);
        setLoading(false);
      } catch (error) {
        toast.error("Error fetching failed payments.");
        setLoading(false);
      }
    };

    fetchFailedPayments();
  }, []);

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:9500/payment/delete/${id}`);
      setFailedPayments((prev) => prev.filter((payment) => payment.id !== id));
      toast.success("Failed payment deleted successfully!");
    } catch (error) {
      toast.error("Error deleting failed payment.");
    }
  };

  const handleViewRawData = (rawData) => {
    setSelectedRawData(rawData);
    setIsDialogOpen(true);
  };

  const lastPage = currentPage * paymentsPerPage;
  const firstPage = lastPage - paymentsPerPage;
  const currentPayments = failedPayments.slice(firstPage, lastPage);

  return (
    <div className="failed-container">
      <Typography variant="h4" align="center" gutterBottom>
        Failed Transactions
      </Typography>

      {loading ? (
        <CircularProgress className="loading-spinner" />
      ) : (
        <TableContainer component={Paper} className="table-container">
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell>Error Message</TableCell>
                <TableCell>Raw Data</TableCell>
                <TableCell>Action</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {currentPayments.length > 0 ? (
                currentPayments.map((payment) => (
                  <TableRow key={payment.id}>
                    <TableCell>{payment.id}</TableCell>
                    <TableCell>{payment.errorMessage}</TableCell>
                    <TableCell>
                      <Button variant="contained" onClick={() => handleViewRawData(payment.rawData)}>
                        View Raw Data
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
                  <TableCell colSpan={4} align="center">
                    No failed payments found.
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>
      )}

      <Pagination
        count={Math.ceil(failedPayments.length / paymentsPerPage)}
        page={currentPage}
        onChange={(event, value) => setCurrentPage(value)}
        className="pagination"
      />

      {/* Dialog for Raw Data */}
      <Dialog open={isDialogOpen} onClose={() => setIsDialogOpen(false)} fullWidth maxWidth="md">
        <DialogTitle>Raw Data</DialogTitle>
        <DialogContent>
          <pre style={{ whiteSpace: "pre-wrap", wordWrap: "break-word" }}>
            {selectedRawData ? JSON.stringify(selectedRawData, null, 2) : "No raw data available"}
          </pre>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setIsDialogOpen(false)}>Close</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default FailedPayments;
