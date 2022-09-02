package com.tekgs.nextgen.tekegg.view.cart;

import com.tekgs.nextgen.tekegg.view.cart.product.ProductsRegionCalibratable;

public interface CartViewCalibratable {
    String getTotalAmount();

    ProductsRegionCalibratable inProducts();
}
