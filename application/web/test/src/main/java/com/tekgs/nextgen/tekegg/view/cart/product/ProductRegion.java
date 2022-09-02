package com.tekgs.nextgen.tekegg.view.cart.product;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiRegion;

public class ProductRegion extends UiRegion implements ProductRegionCalibratable {
    protected ProductRegion(UiElement productElement) {
        super(productElement);
    }

    public static ProductRegion getInstance(UiElement productElement) {
        return new ProductRegion(productElement);
    }

    @Override
    public boolean equivalent(ProductRegionCalibratable comparator) {
        return comparator.getProductDescription() == null || this.getProductDescription().equals(comparator.getProductDescription());
    }

    @Override
    public String getProductDescription() {
        return getDescriptionElement().getText();
    }

    private UiElement getDescriptionElement() {
        return UiElement.getInstance("Description", UiLocatorType.CLASS, "description", this.getElement());
    }
}
