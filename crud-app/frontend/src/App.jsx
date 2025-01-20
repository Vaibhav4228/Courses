
import './App.css'
import Navbar from './navbar/Navbar.jsx'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import Home from './pages/Home.jsx'
import Add from './user/Add.jsx';
import Edit  from './user/Edit.jsx';
import View from './user/View.jsx';
function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />

        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/adduser" element={<Add />} />
          <Route exact path="/edituser/:id" element={<Edit />} />
          <Route exact path="/viewuser/:id" element={<View />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App
