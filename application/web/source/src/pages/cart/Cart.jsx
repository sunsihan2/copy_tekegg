import React, { useEffect, useState } from "react";
import getTotal from "../../../javascript/totals/getTotal";
import convertToDollars from "../../../javascript/totals/convertToDollars.js";
import cartsData from "../../carts.json";

function Cart() {
  const [total, setTotal] = useState(0);

  const [products, setProducts] = useState([]);

  useEffect(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const cartId = parseInt(urlParams.get("cart_id"));
    if (cartId === null) {
      return;
    }
    for (const cart of cartsData) {
      if (cart.id === cartId) {
        setProducts(cart.products);
        setTotal(convertToDollars(cart.total));
      }
    }
  }, []);
  const cleanProductDescription = (description) => {
    const hiddenBackspaceCharacter = "(U+2408)";
    if (description == null || description.includes(hiddenBackspaceCharacter)) {
      return "";
    }
    return description;
  };

  const getProducts = () => {
    return products.length > 0 ? (
      <ul id="products">
        {products.map((product) => (
          <li className="product" key={product.id}>
            <h3>{product.title}</h3>
            <p className="description">
              {cleanProductDescription(product.description)}
            </p>
          </li>
        ))}
      </ul>
    ) : null;
  };
  return (
    <div id="cart">
      <h1>Cart Page</h1>
      {getProducts()}
      <h2 className="payment-total">Total: </h2>
      <span id="total"> {total.toFixed(2)}</span>
    </div>
  );
}

export default Cart;
