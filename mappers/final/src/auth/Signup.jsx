import { useState } from "react";
import { useAuth } from "./AuthContext";
import { Link } from "react-router-dom";
import "../styles/signup.css";

const Signup = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const { signup } = useAuth();

  const handleSubmit = (e) => {
    e.preventDefault();
    signup(username, password);
  };

  return (
    <div className="signup-container">
      <div className="signup-box">
        <h2>Signup</h2>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button type="submit">Sign Up</button>
        </form>
        <Link to="/login" className="signup-toggle">
          Already have an account? Log In
        </Link>
      </div>
    </div>
  );
};

export default Signup;
