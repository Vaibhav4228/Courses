import { useNavigate } from "react-router-dom";
import { motion } from "framer-motion";
import Typewriter from "typewriter-effect";
import "./LandingPage.css";

const LandingPage = () => {
  const navigate = useNavigate();

  return (
    <div className="landing-container">
      <div className="landing-content">
        {/* Typewriter Heading */}
        <motion.h1
          initial={{ opacity: 0, y: -20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 1 }}
        >
          <Typewriter
            options={{
              strings: ["Welcome to Discounted Spring-Batch App"],
              autoStart: true,
              loop: true,
            }}
          />
        </motion.h1>

        {/* Animated Paragraph */}
        <motion.p
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 1, delay: 0.5 }}
        >
          Manage your products with ease and efficiency.
        </motion.p>

        {/* Animated Button */}
        <motion.button
          onClick={() => navigate("/form")}
          initial={{ opacity: 0, scale: 0.8 }}
          animate={{ opacity: 1, scale: 1 }}
          transition={{ duration: 0.5, delay: 1 }}
          whileHover={{ scale: 1.1 }}
          whileTap={{ scale: 0.95 }}
        >
          Get Started
        </motion.button>
      </div>
    </div>
  );
};

export default LandingPage;
