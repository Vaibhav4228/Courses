import { useState } from "react";
import { TextField, Button, Typography, Box } from "@mui/material";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./ProductForm.css"; 

const ProductForm = () => {
  const [product, setProduct] = useState({ id: "", name: "", price: "", category: "" });
  const navigate = useNavigate(); 

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProduct({ ...product, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();


    alert("Product added");

    const productData = {
      id: product.id,
      name: product.name,
      originalPrice: parseFloat(product.price),
      category: product.category
    };

    try {
      const response = await axios.post("http://localhost:8080/products/add", productData);
      
      alert(`Product added successfully! ${response.data.message || ""}`);
    } catch (error) {
      console.error("Error adding product:", error);
      alert("There was an error adding the product.");
    }
  };


  const handleProcess = async () => {
    try {
      await axios.post("http://localhost:8080/products/process");
      alert("Batch processing started successfully!");
      navigate("/discounted");
    } catch (error) {
      console.error("Error processing data:", error);
      alert("There was an error during batch processing.");
    }
  };

  return (
    <Box className="form-container">
      <Typography variant="h4">Add Product</Typography>
      <form onSubmit={handleSubmit} className="product-form">
        <TextField
          label="Enter ID"
          variant="outlined"
          name="id"
          value={product.id}
          onChange={handleInputChange}
          fullWidth
          margin="normal"
        />
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
        
        <Button type="submit" variant="contained" color="primary" fullWidth  alert    >
          Add Product
        </Button>
      </form>

      

      <Button variant="contained" color="secondary" onClick={handleProcess} fullWidth>
        Start Batch Processing 🤗🙂
      </Button>
    </Box>
  );
};

export default ProductForm;
