import React, { useState } from "react";
import { TextField, Button, CircularProgress } from "@mui/material";
import { motion } from "framer-motion";
import { toast } from "react-toastify";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../styles/ConvertPayment.css";

const ConvertPayment = () => {
  const [inputData, setInputData] = useState("");
  const [convertedData, setConvertedData] = useState(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleConvert = async () => {
    try {
      setLoading(true);
      const jsonPayload = JSON.parse(inputData);
      const response = await axios.post("http://localhost:9000/payment/change", jsonPayload);
      setConvertedData(response.data);
      toast.success("Conversion successful!");
    } catch (error) {
      toast.error("Invalid JSON or API error");
    } finally {
      setLoading(false);
    }
  };

  const handleProceedToSave = () => {
    navigate("/save-payment", { state: { convertedData } });
  };

  return (
    <div className="convert-container">
      <motion.h2 initial={{ opacity: 0 }} animate={{ opacity: 1 }} transition={{ duration: 1 }}>
        Convert Payment Data
      </motion.h2>
      <TextField
        label="Enter Payload Data"
        multiline
        rows={8}
        variant="outlined"
        fullWidth
        value={inputData}
        onChange={(e) => setInputData(e.target.value)}
        className="input-box"
      />
      <Button
        variant="contained"
        color="primary"
        onClick={handleConvert}
        disabled={loading}
        className="convert-button"
      >
        {loading ? <CircularProgress size={24} /> : "Convert"}
      </Button>
      {convertedData && (
        <>
          <motion.pre
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            transition={{ duration: 1 }}
            className="output-box"
          >
            {JSON.stringify(convertedData, null, 2)}
          </motion.pre>
          <Button
            variant="contained"
            color="success"
            onClick={handleProceedToSave}
            className="proceed-button"
          >
            Proceed to Save
          </Button>
        </>
      )}
    </div>
  );
};

export default ConvertPayment;
