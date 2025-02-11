import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:6000/payment",
  headers: { "Content-Type": "application/json" },
});

export function convertPayment(payload) {
  return apiClient.post("/change", payload);
}

export function savePayment(payload) {
  return apiClient.post("/Saving-DB", payload);
}

export function getSuccessfulPayments() {
  return apiClient.get("/Successfull-Payment");
}

export function getFailedPayments() {
  return apiClient.get("/Failed-Payments");
}

export function deletePayment(id) {
  return apiClient.delete(`/delete/${id}`);
}

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  convertPayment,
  savePayment,
  getSuccessfulPayments,
  getFailedPayments,
  deletePayment,
};
