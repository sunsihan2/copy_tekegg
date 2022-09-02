package com.tekgs.nextgen.tekegg.data.cart;

public class CartProvider {
    public static CartProvider getInstance() {
        return new CartProvider();
    }

    public Cart get() {
        return Cart.getInstance();
    }

    public Cart get(CartDefinition cartDefinition) {
        return CartRepository.getInstance().query(cartDefinition);
    }
}
