import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function View() {
  const [user, setUser] = useState({
    name: "",
    username: "",
    email: "",
  });

  const { id } = useParams();

  useEffect(() => {
    loadUser();
  }, []);

  const loadUser = async () => {
    const result = await axios.get(`http://localhost:8080/user/${id}`);
    setUser(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">User Details</h2>

          <div className="card">
            <div className="card-body">
              <h5 className="card-title">Name: {user.name}</h5>
              <h5 className="card-title">Username: {user.username}</h5>
              <h5 className="card-title">Email: {user.email}</h5>
            </div>
          </div>

          <Link className="btn btn-outline-success my-2" to="/">
            Back to Home
          </Link>
        </div>
      </div>
    </div>
  );
}



