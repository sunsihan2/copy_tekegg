package com.tekgs.nextgen.tekegg.view.cart;

import com.softwareonpurpose.calibrator4test.Calibrator;
import com.softwareonpurpose.uinavigator.UiRegion;
import com.tekgs.nextgen.tekegg.view.cart.product.ProductRegionCalibratable;
import com.tekgs.nextgen.tekegg.view.cart.product.ProductRegionCalibrator;

import java.util.List;

public class CartViewCalibrator extends Calibrator {
    private static final String DESCRIPTION = "'Cart' view";
    private final CartViewExpected expected;
    private final CartView actual;

    protected CartViewCalibrator(CartViewExpected expected, CartView actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
        UiRegion.suppressConstructionLogging(true);
        List<ProductRegionCalibratable> expectedProducts = expected.inProducts().getProducts();
        List<ProductRegionCalibratable> actualProducts = actual.inProducts().getProducts();
        for (ProductRegionCalibratable expectedProduct : expectedProducts) {
            ProductRegionCalibratable toRemove = null;
            boolean found = false;
            for (ProductRegionCalibratable candidate : actualProducts) {
                if (candidate.equivalent(expectedProduct)) {
                    found = true;
                    addChildCalibrator(ProductRegionCalibrator.getInstance(expectedProduct, candidate));
                    toRemove = candidate;
                    break;
                }
            }
            if (!found) {
                addChildCalibrator(ProductRegionCalibrator.getInstance(expectedProduct, null));
            }
            actualProducts.remove(toRemove);
        }
        for (ProductRegionCalibratable actualProduct : actualProducts) {
            addChildCalibrator(ProductRegionCalibrator.getInstance(null, actualProduct));
        }
        UiRegion.suppressConstructionLogging(false);
    }

    public static CartViewCalibrator getInstance(CartViewExpected expected, CartView actual) {
        return new CartViewCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("Total amount", expected.getTotalAmount(), actual.getTotalAmount());
    }
}
