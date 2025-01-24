import { Link } from 'react-router-dom';
import { useAuth } from './security/AuthContext';

function HeaderComponent() {
    const authContext = useAuth()
    const isAuthenticated = authContext.isAuthenticated

    function logout() {
        authContext.logout()
    }

    return (
        <header className="border-bottom border-light border-5 mb-5 p-2">
            <div className="container">
                <nav className="navbar navbar-expand-lg">
                    <Link className="navbar-brand" to="/">TodoApp</Link>
                    <ul className="navbar-nav">
                        {isAuthenticated && <li><Link className="nav-link" to="/todos">Todos</Link></li>}
                        {isAuthenticated && <li><Link className="nav-link" to="/logout" onClick={logout}>Logout</Link></li>}
                        {!isAuthenticated && <li><Link className="nav-link" to="/login">Login</Link></li>}
                    </ul>
                </nav>
            </div>
        </header>
    );
}

export default HeaderComponent;
