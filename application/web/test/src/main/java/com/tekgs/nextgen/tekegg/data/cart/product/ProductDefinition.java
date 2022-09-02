package com.tekgs.nextgen.tekegg.data.cart.product;

public class ProductDefinition implements ProductCalibratable {
    @SuppressWarnings("unused")
    private String description;
    private String title;
    private Long id;

    public ProductDefinition(String description, String title, Long id) {
        this.description = description;
        this.title = title;
        this.id = id;
    }

    public static ProductDefinition getInstance() {
        return new ProductDefinition(null, null, null);
    }


    @Override
    public String getDescription() {
        return description;
    }

    public ProductDefinition withDescription(String description) {
        String hiddenBackspaceCharacter = "(U+2408)";
        if (description == null || description.contains(hiddenBackspaceCharacter)) {
            this.description = "";
            return this;
        }
        this.description = description;
        return this;
    }
}
