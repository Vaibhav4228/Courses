import React, { useState } from "react";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../utils/axiosInstance";
import "../styles/MakePayment.css";

const MakePayment = () => {
  const [amount, setAmount] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const handlePayment = async () => {
    try {
      setLoading(true);
      const orderResponse = await axiosInstance.post("http://localhost:9500/razorpay/order", {
        amount: parseFloat(amount),
        currency: "INR",
        receipt: "receipt#1"
      });
      const { orderId } = orderResponse.data;
      const options = {
        key: process.env.REACT_APP_RAZORPAY_KEY,
        amount: parseFloat(amount) * 100,
        currency: "INR",
        name: "Vaibhav",
        description: "Test Transaction",
        order_id: orderId,
        handler: function (response) {
          toast.success("Payment successful!");
          navigate("/");
        },
        prefill: {
          name: "vaibhav",
          email: "vaibhav@gmail.com",
          contact: "6268111086",
        },
        theme: {
          color: "#3399cc",
        },
      };
      const rzp = new window.Razorpay(options);
      rzp.open();
    } catch (error) {
      console.log(error);
      toast.error("Error during payment.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="make-payment-container">
      <div className="make-payment-content">
        <h2>Make a Payment</h2>
        <div className="input-row">
          <label>Amount (INR):</label>
          <input
            type="number"
            step="0.01"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
          />
        </div>
        <button
          onClick={handlePayment}
          disabled={loading || !amount}
          className="payment-button"
        >
          {loading ? "Loading..." : "Pay Now"}
        </button>
      </div>
    </div>
  );
};
export default MakePayment;
