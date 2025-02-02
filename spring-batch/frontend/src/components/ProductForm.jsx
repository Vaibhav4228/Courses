import { useState } from "react";
import { TextField, Button, Typography, Box } from "@mui/material";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./ProductForm.css";

const ProductForm = () => {
  const navigate = useNavigate();

  // Define initial state
  const [product, setProduct] = useState({
    name: "",
    price: "",
    category: "",
  });

 
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProduct((prevProduct) => ({
      ...prevProduct,
      [name]: name === "price" ? (value ? parseFloat(value) : "") : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();


    if (!product.name || !product.price || !product.category) {
      alert("All fields are required!");
      return;
    }

    const productData = {
      name: product.name,
      originalPrice: product.price, 
      category: product.category,
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/products/add",
        productData,
        {
          headers: { "Content-Type": "application/json" },
        }
      );

      alert("Product added successfully!");
      setProduct({ name: "", price: "", category: "" }); 
    } catch (error) {
      console.error("Error adding product:", error);
      alert("Failed to add product. Please try again.");
    }
  };

 
  const handleProcess = async () => {
    try {
      await axios.post("http://localhost:8080/products/process");
      alert("Batch processing started successfully!");
      navigate("/discounted");
    } catch (error) {
      console.error("Error processing data:", error);
      alert("Batch processing failed.");
    }
  };

  return (
    <Box className="form-container">
      <Typography variant="h4">Add Product</Typography>
      <form onSubmit={handleSubmit} className="product-form">
        <TextField
          label="Product Name"
          variant="outlined"
          name="name"
          value={product.name}
          onChange={handleInputChange}
          fullWidth
          margin="normal"
        />
        <TextField
          label="Product Price"
          variant="outlined"
          name="price"
          type="number"
          value={product.price}
          onChange={handleInputChange}
          fullWidth
          margin="normal"
        />
        <TextField
          label="Product Category"
          variant="outlined"
          name="category"
          value={product.category}
          onChange={handleInputChange}
          fullWidth
          margin="normal"
        />
        <Button type="submit" variant="contained" color="primary" fullWidth>
          Add Product
        </Button>
      </form>
      <Button
        variant="contained"
        color="secondary"
        onClick={handleProcess}
        fullWidth
        style={{ marginTop: "10px" }}
      >
        Start Batch Processing
      </Button>
    </Box>
  );
};

export default ProductForm;
