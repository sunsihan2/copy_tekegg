package com.tekgs.nextgen.tekegg.view.cart;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import com.softwareonpurpose.uinavigator.web.WebUiHost;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;
import org.softwareonpurpose.gauntlet.Environment;

public class CartView extends UiView implements CartViewCalibratable {
    private static final String LOCATOR_VALUE = "cart";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String DESCRIPTION = " 'Payment' view";
    private static final String DOMAIN = Environment.getInstance().getDomainUrl();
    private static final String VIEW_URI = String.format("%s/cart", DOMAIN);

    public CartView() {
        super(VIEW_URI, UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE));
    }

    public static CartView directNav() {
        new CartView().load();
        return UiView.expect(CartView.class);
    }

    public static CartView directNav(CartCalibratable cart) {
        new CartView().load(String.format("?cart_id=%d", cart.getId()));
        return UiView.expect(CartView.class);
    }

    @Override
    protected boolean confirmElementStates() {
        return WebUiHost.getInstance().getAddress().contains(VIEW_URI);
    }

    @Override
    public String getTotalAmount() {
        return getTotalAmountElement().getText();
    }

    @Override
    public ProductsRegion inProducts() {
        return ProductsRegion.getInstance(this.getElement());
    }

    private UiElement getTotalAmountElement() {
        String description = "Total amount";
        String locatorValue = "total";
        return UiElement.getInstance(description, UiLocatorType.ID, locatorValue, this.getElement());
    }
}
