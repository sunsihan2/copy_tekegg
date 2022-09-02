package com.tekgs.nextgen.tekegg.view.cart.product;

import com.tekgs.nextgen.tekegg.data.cart.product.ProductCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.product.ProductDefinition;

import java.util.ArrayList;
import java.util.List;

public class ProductsRegionExpected implements ProductsRegionCalibratable {
    @SuppressWarnings({"FieldCanBeLocal", "MismatchedQueryAndUpdateOfCollection"})
    private final List<ProductCalibratable> products = new ArrayList<>();

    public ProductsRegionExpected(List<ProductDefinition> products) {
        this.products.addAll(products);
    }

    public static ProductsRegionExpected getInstance(List<ProductDefinition> products) {
        return new ProductsRegionExpected(products);
    }

    @Override
    public List<ProductRegionCalibratable> getProducts() {
        List<ProductRegionCalibratable> productRegions = new ArrayList<>();
        for (ProductCalibratable product : products) {
            productRegions.add(ProductRegionExpected.getInstance(product));
        }
        return productRegions;
    }
}
