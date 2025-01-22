import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from './security/AuthContext';

function LoginComponent() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState(false);

    const navigate = useNavigate();
    const authContext = useAuth();

    function handleSubmit() {
        if (authContext.login(username, password)) {
            navigate(`/welcome/${username}`);
        } else {
            setErrorMessage(true);
        }
    }

    return (
        <div className="Login">
            <h1>Time to Login!</h1>
            {errorMessage && <div className="errorMessage">Authentication Failed. Please check your credentials.</div>}
            <div className="LoginForm">
                <div>
                    <label>Username:</label>
                    <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </div>
                <button type="button" onClick={handleSubmit}>Login</button>
            </div>
        </div>
    );
}

export default LoginComponent;


