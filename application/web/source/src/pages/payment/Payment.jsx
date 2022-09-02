import React, { useState, useEffect } from "react";
import getTotal from "../../../javascript/totals/getTotal";
import convertToDollars from "../../../javascript/totals/convertToDollars.js";
import postPayment from "./postPayment";
import parsePaymentResponse from "./parsePaymentResponse";
import { Navigate } from "react-router-dom";

function Payment() {
  const [total, setTotal] = useState(0);
  const [purchase, setPurchase] = useState();
  const [currencyErrorMessage, setCurrencyErrorMessage] = useState(false);
  const [sourceErrorMessage, setSourceErrorMessage] = useState(false);
  const [formData, setFormData] = useState({
    "payment-currency": "",
    "payment-source": "",
  });

  useEffect(() => {
    setTotal(convertToDollars(getTotal()));
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    const isValid = validateFields();
    if (isValid) {
      postPayment(
        getTotal(),
        formData["payment-currency"],
        formData["payment-source"]
      ).then((data) => {
        setPurchase(parsePaymentResponse(data));
      });
    }
  };

  function validateFields() {
    let isCurrencyValid = false;
    let isValid = false;
    let isSourceValid = false;
    const validSources = [
      "tok_amex",
      "tok_visa",
      "tok_visa_debit",
      "tok_mastercard",
      "tok_mastercard_debit",
      "tok_mastercard_prepaid",
      "tok_discover",
      "tok_diners",
      "tok_jcb",
      "tok_unionpay",
    ];

    isCurrencyValid = formData["payment-currency"] === "usd";
    setCurrencyErrorMessage(!isCurrencyValid);

    isSourceValid = validSources.includes(formData["payment-source"]);
    setSourceErrorMessage(!isSourceValid);

    isValid = isCurrencyValid && isSourceValid;
    return isValid;
  }

  function handleChange(e) {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  }

  return (
    <div id="payment">
      <h1>Payment Page</h1>
      <h2 className="payment-total">Total: </h2>
      <span id="total"> {total.toFixed(2)}</span>

      {purchase !== undefined ? <Navigate to={`/purchase/${purchase}`} /> : ""}
      <form onSubmit={(e) => handleSubmit(e)}>
        <label htmlFor="currency-input">Currency</label>
        <input
          name="payment-currency"
          onChange={(e) => handleChange(e)}
          type="text"
          value={formData["payment-currency"]}
        />
        <p id="currency-error-message" className="error-message">
          {currencyErrorMessage ? "Currency invalid" : ""}
        </p>
        <label htmlFor="payment-source">Source</label>
        <input
          name="payment-source"
          onChange={(e) => handleChange(e)}
          type="text"
          value={formData["payment-source"]}
        />
        <p id="source-error-message" className="error-message">
          {sourceErrorMessage ? "Source invalid" : ""}
        </p>
        <input type="submit" id="submit" value="submit" />
      </form>
    </div>
  );
}

export default Payment;
