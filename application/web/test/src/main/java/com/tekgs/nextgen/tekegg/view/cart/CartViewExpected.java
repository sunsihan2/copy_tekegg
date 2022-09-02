package com.tekgs.nextgen.tekegg.view.cart;

import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.product.ProductDefinition;
import com.tekgs.nextgen.tekegg.data.value.Cents;
import com.tekgs.nextgen.tekegg.view.cart.product.ProductsRegionExpected;

import java.util.ArrayList;
import java.util.List;

public class CartViewExpected implements CartViewCalibratable {
    private final CartCalibratable cart;
    private final Cents cents;

    public CartViewExpected(CartCalibratable cart) {
        this.cart = cart;
        Integer total = cart == null ? 0 : cart.getTotal();
        this.cents = Cents.getInstance(total);
    }

    public static CartViewExpected getInstance() {
        return new CartViewExpected(null);
    }

    public static CartViewExpected getInstance(CartCalibratable cart) {
        return new CartViewExpected(cart);
    }

    @Override
    public String getTotalAmount() {
        return cents.inDollarFormat();
    }

    @Override
    public ProductsRegionExpected inProducts() {
        List<ProductDefinition> products = cart == null ? new ArrayList<>() : cart.getProducts();
        return ProductsRegionExpected.getInstance(products);
    }
}
