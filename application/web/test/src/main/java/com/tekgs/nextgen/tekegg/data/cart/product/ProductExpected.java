package com.tekgs.nextgen.tekegg.data.cart.product;

public class ProductExpected implements ProductCalibratable {
    private final String description;

    public ProductExpected(String description) {
        this.description = description;
    }

    public static ProductExpected getInstance() {
        return new ProductExpected(null);
    }

    public static ProductExpected getInstance(String description) {
        return new ProductExpected(description);
    }

    @Override
    public String getDescription() {
        return description;
    }
}
