import { createContext, useContext, useState } from 'react';
import { executeBasicAuthenticationService } from "../api/AuthenticationApiService";


export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({ children }) {
    const [isAuthenticated, setAuthenticated] = useState(false);

    const[username, setUsername] = useState(null);

    const [token, setToken] = useState(null);

   async  function login(username, password) {
       
        const baToken = 'Basic ' + window.btoa( username + ":" + password )

      const response =  await executeBasicAuthenticationService(baToken)
try {

    const response = await executeBasicAuthenticationService(baToken)
    if(response.status === 200){
        
        setAuthenticated(true);
        setUsername(username);
        return true;

      }
      else {
        setAuthenticated(false);
        setUsername(null);
        
        return false;
      }
    
} catch (error) {
    setAuthenticated(false)
    setUsername(username)
    return false
    
}
   }

    

        
    

    function logout() {
        setAuthenticated(false);
        setUsername(null);
        return false
    }

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout, username }}>
        {children}
    </AuthContext.Provider>
    );

    
}


