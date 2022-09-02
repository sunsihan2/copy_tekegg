package com.tekgs.nextgen.tekegg.data.cart;

import com.tekgs.nextgen.tekegg.data.cart.product.ProductDefinition;

import java.util.List;

public interface CartCalibratable {
    Integer getTotal();

    Long getId();

    List<ProductDefinition> getProducts();

    boolean equivalent(CartDefinition cartDefinition);
}
