
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import ProductForm from "./components/ProductForm";
import DiscountedProducts from "./components/DiscountedProducts";
import { Container } from "@mui/material";
import "./index.css"; 

const App = () => {
  return (
    <Router>
      <Container>
        <Routes>
          <Route path="/" element={<ProductForm />} />
          <Route path="/discounted" element={<DiscountedProducts />} />
        </Routes>
      </Container>
    </Router>
  );
};

export default App;
