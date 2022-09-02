package com.tekgs.nextgen.tekegg.view.cart;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiRegion;
import com.tekgs.nextgen.tekegg.view.cart.product.ProductRegion;
import com.tekgs.nextgen.tekegg.view.cart.product.ProductRegionCalibratable;
import com.tekgs.nextgen.tekegg.view.cart.product.ProductsRegionCalibratable;

import java.util.ArrayList;
import java.util.List;

public class ProductsRegion extends UiRegion implements ProductsRegionCalibratable {
    private static final String DESCRIPTION = "'Products' region";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String LOCATOR_VALUE = "products";

    protected ProductsRegion(UiElement parentElement) {
        super(UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE, parentElement));
    }

    public static ProductsRegion getInstance(UiElement parent) {
        return new ProductsRegion(parent);
    }

    @Override
    public List<ProductRegionCalibratable> getProducts() {
        List<ProductRegionCalibratable> products = new ArrayList<>();
        for (UiElement productElement : UiElement.getList("Product", UiLocatorType.CLASS, "product", this.getElement())) {
            products.add(ProductRegion.getInstance(productElement));
        }
        return products;
    }
}
