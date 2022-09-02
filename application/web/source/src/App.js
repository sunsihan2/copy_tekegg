import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import Cart from "./pages/Cart/Cart";
import Error404 from "./pages/Error/Error404";
import Landing from "./pages/Landing/Landing";
import Payment from "./pages/Payment/Payment";
import Purchase from "./pages/Purchase/purchase";
import "./App.css";

function App() {
  return (
    <div>
      <Routes>
        <Route path="*" element={<Navigate to="/404-error" replace />} />
        <Route path="/404-error" element={<Error404 />} />
        <Route path="/" element={<Landing />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/payment" element={<Payment />} />
        <Route path="/purchase/:result" element={<Purchase />} />
      </Routes>
    </div>
  );
}

export default App;
