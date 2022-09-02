package com.tekgs.nextgen.tekegg.data.cart;

import com.tekgs.nextgen.tekegg.data.cart.product.ProductDefinition;

import java.util.ArrayList;
import java.util.List;

public class Cart implements CartCalibratable {
    private final Integer total;
    private final List<ProductDefinition> products = new ArrayList<>();
    private final Long id;

    public Cart(CartDefinition cartDefinition) {
        this.total = cartDefinition == null ? 0 : cartDefinition.getTotal();
        if (cartDefinition != null && cartDefinition.getProducts() != null) {
            products.addAll(cartDefinition.getProducts());
        }
        this.id = cartDefinition != null && cartDefinition.getId() != null ?  cartDefinition.getId() : -1;
    }

    public static Cart getInstance() {
        return new Cart(null);
    }

    public static Cart getInstance(CartDefinition cartDefinition) {
        return new Cart(cartDefinition);
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public Integer getTotal() {
        return total;
    }

    @Override
    public List<ProductDefinition> getProducts() {
        return products;
    }

    @Override
    public boolean equivalent(CartDefinition cartDefinition) {
        return false;
    }
}
