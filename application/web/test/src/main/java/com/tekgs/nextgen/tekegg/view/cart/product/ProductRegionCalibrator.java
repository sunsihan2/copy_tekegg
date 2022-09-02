package com.tekgs.nextgen.tekegg.view.cart.product;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class ProductRegionCalibrator extends Calibrator {
    private static final String DESCRIPTION = "'Product' region";
    private ProductRegionCalibratable expected;
    private ProductRegionCalibratable actual;


    private ProductRegionCalibrator(ProductRegionCalibratable expected, ProductRegionCalibratable actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }

    public static ProductRegionCalibrator getInstance(ProductRegionCalibratable expected, ProductRegionCalibratable actual) {
        return new ProductRegionCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("Product Description", expected.getProductDescription(), actual.getProductDescription());
    }
}
