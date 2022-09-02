package com.tekgs.nextgen.tekegg.data.value;

public class Cents {
    private final Integer amount;

    public Cents(Integer amount) {
        this.amount = amount == null ? 0 : amount;
    }

    public static Cents getInstance(Integer amount) {
        return new Cents(amount);
    }

    public String inDollarFormat() {
        return String.format("%.2f", (double) amount / 100);
    }
}
