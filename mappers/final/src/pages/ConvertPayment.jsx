import React, { useState, useCallback } from "react";
import { TextField, Button, CircularProgress } from "@mui/material";
import { toast } from "react-toastify";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { debounce, throttle } from "lodash";
import "../styles/ConvertPayment.css";

const ConvertPayment = () => {
  const [inputData, setInputData] = useState("");
  const [convertedData, setConvertedData] = useState(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const debouncedInputChange = useCallback(
    debounce((value) => {
      setInputData(value);
    }, 500), 
    []
  );

  
  const throttledConvert = useCallback(
    throttle(async () => {
      try {
        setLoading(true);
        const jsonPayload = JSON.parse(inputData);
        const response = await axios.post("http://localhost:9500/payment/change", jsonPayload);
        setConvertedData(response.data);
        toast.success("Conversion successful!");
      } catch (error) {
        toast.error("Invalid JSON or API error");
      } finally {
        setLoading(false);
      }
    }, 3000), 
    [inputData]
  );

  const handleProceedToSave = () => {
    navigate("/save-payment", { state: { convertedData } });
  };

  return (
    <div className="convert-container">
      <h2>Convert Payment Data</h2>
      <TextField
        label="Enter Payload Data"
        multiline
        rows={8}
        variant="outlined"
        fullWidth
        defaultValue={inputData}
        onChange={(e) => debouncedInputChange(e.target.value)}
        className="input-box"
      />
      <Button
        variant="contained"
        color="primary"
        onClick={throttledConvert}
        disabled={loading}
        className="convert-button"
      >
        {loading ? <CircularProgress size={24} /> : "Convert"}
      </Button>
      {convertedData && (
        <>
          <pre className="output-box">
            {JSON.stringify(convertedData, null, 2)}   
          </pre>
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
