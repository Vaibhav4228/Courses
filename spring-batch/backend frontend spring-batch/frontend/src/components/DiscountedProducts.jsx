import { useEffect, useState } from "react";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, Pagination, Typography, CircularProgress } from "@mui/material";
import axios from "axios";
import "./DiscountedProducts.css";

const DiscountedProducts = () => {
  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(1);
  const [loading, setLoading] = useState(true);
  const itemsPerPage = 10;

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axios.get("http://localhost:9000/products/discounted");
      if (Array.isArray(response.data)) {
        setProducts(response.data);
      } else {
        console.error("Invalid API response format:", response.data);
      }
    } catch (error) {
      console.error("Error fetching discounted products:", error);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:9000/products/delete/${id}`);
      setProducts(products.filter((product) => product.id !== id)); 
    } catch (error) {
      console.error("Error deleting product:", error);
    }
  };

  const handlePageChange = (event, value) => {
    setPage(value);
  };

  const displayedProducts = products.slice((page - 1) * itemsPerPage, page * itemsPerPage);

  return (
    <div className="discounted-container">
      <Typography variant="h4" gutterBottom className="discounted-heading">
        Discounted Products
      </Typography>

      {loading ? (
        <div className="loading-container">
          <CircularProgress />
          <Typography variant="h6">Loading products...</Typography>
        </div>
      ) : products.length === 0 ? (
        <Typography variant="h6" color="textSecondary">
          No discounted products available.
        </Typography>
      ) : (
        <>
          <TableContainer component={Paper} className="table-container">
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell><strong>ID</strong></TableCell>
                  <TableCell><strong>Name</strong></TableCell>
                  <TableCell><strong>Category</strong></TableCell>
                  <TableCell><strong>Price</strong></TableCell>
                  <TableCell><strong>Discounted Price</strong></TableCell>
                  <TableCell><strong>Actions</strong></TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {displayedProducts.map((product) => (
                  <TableRow key={product.id}>
                    <TableCell>{product.id}</TableCell>
                    <TableCell>{product.name}</TableCell>
                    <TableCell>{product.category}</TableCell>
                    <TableCell>₹{product.originalPrice}</TableCell>
                    <TableCell>₹{product.discountedPrice}</TableCell>
                    <TableCell>
                      <Button 
                        variant="contained" 
                        color="secondary" 
                        onClick={() => handleDelete(product.id)} 
                        style={{ marginLeft: "10px" }}
                      >
                        Delete
                      </Button>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>

          <Pagination 
            count={Math.ceil(products.length / itemsPerPage)} 
            page={page} 
            onChange={handlePageChange} 
            className="pagination" 
          />
        </>
      )}
    </div>
  );
};

export default DiscountedProducts;
