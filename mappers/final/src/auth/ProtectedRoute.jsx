import { Navigate } from "react-router-dom";
import { useAuth } from "./AuthContext";
import { toast } from "react-toastify";

const ProtectedRoute = ({ children }) => {
  const { user } = useAuth();

  if (!user) {
    toast.warn("You are not authorized, please sign up or log in.");
    return <Navigate to="/signup" replace />;
  }

  return children;
};

export default ProtectedRoute;
