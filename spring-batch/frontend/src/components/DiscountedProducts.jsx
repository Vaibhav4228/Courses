import { useEffect, useState } from "react";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, Pagination } from "@mui/material";
import axios from "axios";
import "./DiscountedProducts.css";

const DiscountedProducts = () => {
  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(1);
  const itemsPerPage = 10; 

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axios.get("http://localhost:8080/products/discounted");
      setProducts(response.data);
    } catch (error) {
      console.error("Error fetching discounted products:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/products/delete/${id}`);
      setProducts(products.filter((product) => product.id !== id)); // Remove from UI
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
      <h2>Discounted Products</h2>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Name</TableCell>
              <TableCell>Category</TableCell>
              <TableCell>Original Price</TableCell>
              <TableCell>Discounted Price</TableCell>
              <TableCell>Actions</TableCell>
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
                  <Button variant="contained" color="primary" onClick={() => alert(JSON.stringify(product, null, 2))}>
                    View
                  </Button>
                  <Button variant="contained" color="secondary" onClick={() => handleDelete(product.id)} style={{ marginLeft: "10px" }}>
                    Delete
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <Pagination count={Math.ceil(products.length / itemsPerPage)} page={page} onChange={handlePageChange} className="pagination" />
    </div>
  );
};

export default DiscountedProducts;
