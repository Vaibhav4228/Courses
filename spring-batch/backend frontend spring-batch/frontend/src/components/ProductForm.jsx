/* eslint-disable no-unused-vars */
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./ProductForm.css";

const ProductForm = () => {
  const navigate = useNavigate();
  const [product, setProduct] = useState({
    name: "",
    price: "",
    category: "",
  });
  const [loading, setLoading] = useState(false);

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

    try {
      await axios.post("http://localhost:8080/products/add", product, {
        headers: { "Content-Type": "application/json" },
      });

      alert("Product added successfully!");
      setProduct({ name: "", price: "", category: "" });
    } catch (error) {
      console.error("Error adding product:", error);
      alert("Failed to add product. Please try again.");
    }
  };

  const handleProcess = async () => {
    setLoading(true);

    try {
      await axios.post("http://localhost:8080/products/process");

      setTimeout(() => {
        setLoading(false);
        navigate("/discounted");
      }, 3000);
    } catch (error) {
      console.error("Error processing data:", error);
      alert("Batch processing failed.");
      setLoading(false);
    }
  };

  return (
    <div className="form-container">
      {loading && (
        <div className="loader-overlay">
          <div className="spinner"></div>
        </div>
      )}

      <h2>Add Product</h2>
      <form onSubmit={handleSubmit} className="product-form">
        <input
          type="text"
          name="name"
          value={product.name}
          onChange={handleInputChange}
          placeholder="Product Name"
          className="form-input"
          disabled={loading}
        />
        <input
          type="number"
          name="price"
          value={product.price}
          onChange={handleInputChange}
          placeholder="Product Price"
          className="form-input"
          disabled={loading}
        />
        <input
          type="text"
          name="category"
          value={product.category}
          onChange={handleInputChange}
          placeholder="Product Category"
          className="form-input"
          disabled={loading}
        />
        <button type="submit" className="form-button" disabled={loading}>
          Add Product
        </button>
      </form>
      <button
        onClick={handleProcess}
        className="form-button process-button"
        disabled={loading}
      >
        {loading ? "Processing..." : "Start Batch Processing"}
      </button>
    </div>
  );
};

export default ProductForm;
