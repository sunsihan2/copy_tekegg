package com.tekgs.nextgen.tekegg.view.purchase;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import org.softwareonpurpose.gauntlet.Environment;

public class PurchaseView extends UiView implements PurchaseViewCalibratable {
    private static final String LOCATOR_VALUE = "purchase";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String DESCRIPTION = "'Purchase' view";
    private static final String DOMAIN = Environment.getInstance().getDomainUrl();
    private static final String VIEW_URI = String.format("%s/purchase/false", DOMAIN);

    public PurchaseView() {
        super(VIEW_URI, UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE));
    }

    public static PurchaseView directNav() {
        new PurchaseView().load();
        return UiView.expect(PurchaseView.class);
    }

    @Override
    protected boolean confirmElementStates() {
        return this.getElement().waitUntilVisible();
    }

    @Override
    public String getPayment() {
        return getPurchaseConfirmationElement().getText();
    }

    private UiElement getPurchaseConfirmationElement() {
        String description = "Purchase Confirmation Message";
        String locatorValue = "purchase-confirmation-message";
        return UiElement.getInstance(description, UiLocatorType.ID, locatorValue, this.getElement());
    }
}
