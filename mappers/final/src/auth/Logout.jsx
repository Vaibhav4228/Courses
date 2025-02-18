import { useEffect } from "react";
import { useAuth } from "./AuthContext";

const Logout = () => {
  const { logout } = useAuth();

  useEffect(() => {
    logout();
  }, [logout]);

  return <h2>Logging out...</h2>;
};

export default Logout;
