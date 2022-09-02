package com.stripe.api.charges;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.Response;
import org.softwareonpurpose.gauntlet.Environment;

public class ChargesRequest {
    private static final String PAYMENT_SERVICE_DOMAIN_URL = Environment.getInstance().getPaymentServiceDomainUrl();
    private static final String CHARGES_ENDPOINT = "v1/charges";
    private static final String CHARGES_URL = String.format("%s/%s", PAYMENT_SERVICE_DOMAIN_URL, CHARGES_ENDPOINT);
    private static final String API_KEY = Environment.getInstance().getPaymentAuthorizationKey();
    private final long amount;
    private final String currency;
    private final String source;
    private final Invocation.Builder request;

    private ChargesRequest(long amount, String currency, String source) {
        request = ClientBuilder.newBuilder().build().target(CHARGES_URL).request();
        String authorizationValue = String.format("Bearer %s", API_KEY);
        request.header("Authorization", authorizationValue);
        this.amount = amount;
        this.currency = currency;
        this.source = source;
    }

    public static ChargesRequest getInstance(long amount, String currency, String source) {
        return new ChargesRequest(amount, currency, source);
    }

    public ChargesResponse post() {
        Response response = request.post(compileRequestParameters());
        String responseJson = response.readEntity(String.class);
        response.close();
        return ChargesResponse.getInstance(responseJson);
    }

    private Entity<Form> compileRequestParameters() {
        Form form = new Form()
                .param("amount", String.valueOf(amount))
                .param("currency", currency)
                .param("source", source);
        return Entity.form(form);
    }
}
