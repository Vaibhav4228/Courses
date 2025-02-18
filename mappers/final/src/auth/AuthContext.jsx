import { createContext, useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null); 
  const navigate = useNavigate();

  useEffect(() => {
   
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    }
  }, []);


  const signup = async (username, password) => {
    try {
      const response = await fetch("http://localhost:9500/auth/signup", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText || "Signup failed");
      }

      toast.success("Signup successful! Please log in.");
      navigate("/login");
    } catch (error) {
      toast.error(error.message);
    }
  };


  const login = async (username, password) => {
    try {
      const response = await fetch("http://localhost:9500/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText || "Invalid credentials");
      }
      const data = await response.text(); 
      const userData = { token: data, username }; 

      localStorage.setItem("user", JSON.stringify(userData));
      setUser(userData);

      toast.success("Login successful!");
      navigate("/");
    } catch (error) {
      toast.error(error.message);
    }
  };

  // 🔹 LOGOUT
  const logout = () => {
    localStorage.removeItem("user");
    setUser(null);
    toast.info("Logged out successfully!");
    navigate("/login");
  };

  return (
    <AuthContext.Provider value={{ user, signup, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
