package com.tekgs.nextgen.tekegg.data.cart;


import com.google.gson.Gson;
import com.tekgs.nextgen.tekegg.data.cart.product.ProductDefinition;

import java.util.ArrayList;
import java.util.List;

public class CartDefinition implements CartCalibratable {
    private final List<ProductDefinition> products = new ArrayList<>();
    private Integer total;
    private Long id;

    public static CartDefinition getInstance() {
        return new CartDefinition();
    }


    public CartDefinition withTotal(Integer total) {
        this.total = total;
        return this;
    }

    @Override
    public Integer getTotal() {
        return this.total;
    }

    @Override
    public List<ProductDefinition> getProducts() {
        return products;
    }

    @Override
    public boolean equivalent(CartDefinition cartDefinition) {
        return cartDefinition.getId().equals(this.id);
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("%s %s ", this.getClass().getSimpleName(), new Gson().toJson(this));
    }

    public CartDefinition withProduct(ProductDefinition product) {
        products.add(product);
        return this;
    }

    public CartDefinition withID(Long ID) {
        this.id = ID;
        return this;
    }
}
