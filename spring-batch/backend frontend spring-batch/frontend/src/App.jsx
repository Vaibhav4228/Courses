
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import ProductForm from './components/ProductForm'; 


import LandingPage from './components/LandingPage';
import DiscountedProducts from './components/DiscountedProducts';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/form" element={<ProductForm />} />
        <Route path="/discounted" element={<DiscountedProducts />} />
      </Routes>
    </Router>
  );
};

export default App;