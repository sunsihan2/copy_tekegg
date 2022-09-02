package com.tekgs.nextgen.tekegg.view.cart.product;

import com.tekgs.nextgen.tekegg.data.cart.product.ProductCalibratable;

public class ProductRegionExpected implements ProductRegionCalibratable {
    private final ProductCalibratable product;

    public ProductRegionExpected(ProductCalibratable product) {
        this.product = product;
    }

    public static ProductRegionExpected getInstance(ProductCalibratable product) {
        return new ProductRegionExpected(product);
    }

    @Override
    public boolean equivalent(ProductRegionCalibratable comparator) {
        return false;
    }

    @Override
    public String getProductDescription() {
        return product.getDescription();
    }
}
