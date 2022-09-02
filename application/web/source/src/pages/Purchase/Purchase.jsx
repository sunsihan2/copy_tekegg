import React, { useState } from "react";
import { useParams } from "react-router-dom";

const Purchase = () => {
  const { result } = useParams();
  return (
    <div id="purchase">
      <h1 id="purchase-confirmation-message">
        {result === "true" ? "Purchase was successful" : "Purchase failed"}
      </h1>
    </div>
  );
};

export default Purchase;
