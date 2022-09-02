package com.tekgs.nextgen.tekegg.view.payment;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import com.softwareonpurpose.uinavigator.web.WebUiHost;
import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.payment.TekEggPayment;
import com.tekgs.nextgen.tekegg.view.purchase.PurchaseView;
import org.softwareonpurpose.gauntlet.Environment;

import javax.swing.*;

public class PaymentView extends UiView implements PaymentViewCalibratable {
    private static final String LOCATOR_VALUE = "payment";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String DESCRIPTION = "'Payment' view";
    private static final String DOMAIN = Environment.getInstance().getDomainUrl();
    private static final String VIEW_URI = String.format("%s/payment", DOMAIN);

    public PaymentView() {
        super(VIEW_URI, UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE));
    }

    public static PaymentView directNav() {
        new PaymentView().load();
        return UiView.expect(PaymentView.class);
    }

    public static PaymentView directNav(Cart cart) {
        new PaymentView().load(String.format("?total_amount=%d", cart.getTotal()));
        return UiView.expect(PaymentView.class);
    }

    public static PaymentView directNav(TekEggPayment payment) {
        new PaymentView().load(String.format("?total_amount=%d", payment.getAmount()));
        return UiView.expect(PaymentView.class);
    }


    @Override
    protected boolean confirmElementStates() {
        return WebUiHost.getInstance().getAddress().contains(VIEW_URI);
    }

    @Override
    public String getTotalAmountDue() {
        return getTotalAmountDueElement().getText();
    }

    @Override
    public String getInvalidCurrencyMessage() {
        return getInvalidCurrencyElement().getText();
    }

    @Override
    public String getInvalidSourceMessage() {
        return getInvalidSourceElement().getText();
    }

    private UiElement getInvalidSourceElement() {
        String description = "'Source' error message";
        String locatorValue = "source-error-message";
        return UiElement.getInstance(description, UiLocatorType.ID, locatorValue, this.getElement());
    }

    private UiElement getTotalAmountDueElement() {
        String description = "Total Amount Due";
        String locatorValue = "total";
        return UiElement.getInstance(description, UiLocatorType.ID, locatorValue, this.getElement());
    }

    public PurchaseView submit(TekEggPayment payment) {
        getCurrencyElement().set(payment.getCurrency());
        getSourceElement().set(payment.getSource());
        getSubmitElement().click();
        return UiView.expect(PurchaseView.class);
    }

    public PaymentView submitInvalid(TekEggPayment payment) {
        getCurrencyElement().set(payment.getCurrency());
        getSourceElement().set(payment.getSource());
        getSubmitElement().click();
        return UiView.expect(PaymentView.class);
    }

    private UiElement getCurrencyElement() {
        String description = "Currency";
        String locatorValue = "payment-currency";
        return UiElement.getInstance(description, UiLocatorType.NAME, locatorValue, this.getElement());
    }

    private UiElement getSourceElement() {
        String description = "Source";
        String locatorValue = "payment-source";
        return UiElement.getInstance(description, UiLocatorType.NAME, locatorValue, this.getElement());
    }

    private UiElement getSubmitElement() {
        String description = "'Submit' button";
        String locatorValue = "submit";
        return UiElement.getInstance(description, UiLocatorType.ID, locatorValue, this.getElement());
    }

    private UiElement getInvalidCurrencyElement() {
        String description = "'Currency' error message";
        String locatorValue = "currency-error-message";
        return UiElement.getInstance(description, UiLocatorType.ID, locatorValue, this.getElement());
    }
}
