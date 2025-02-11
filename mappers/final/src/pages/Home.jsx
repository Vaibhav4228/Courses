import { Link } from "react-router-dom";
import Sidebar from "../components/Sidebar";
import "../styles/Home.css";

const Home = () => {
  return (
    <div className="home-page">
      <Sidebar />
      <div className="home-container">
        <div className="hero-section">
          <h1>Welcome to Payment Processing</h1>
          <p>Convert, Save, and Manage Payments Effortlessly</p>
          <Link to="/convert-payment">
            <button className="cta-button">Get Started 🚀</button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Home;
