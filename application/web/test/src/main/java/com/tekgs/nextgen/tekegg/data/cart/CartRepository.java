package com.tekgs.nextgen.tekegg.data.cart;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {
    public static CartRepository getInstance() {
        return new CartRepository();
    }

    public Cart query(CartDefinition cartDefinition) {
        if(cartDefinition.getId() == null) {
            return Cart.getInstance(cartDefinition);
        }
        for (CartDefinition candidate : getCartsFromJSON()) {
            if (candidate.equivalent(cartDefinition)) {
                return Cart.getInstance(cartDefinition);
            }
        }
        return null;
    }

    private List<CartDefinition> getCartsFromJSON() {
        List<CartDefinition> carts = new ArrayList<>();
        Path path = Paths.get("../source/src/carts.json");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            carts = new Gson().fromJson(reader, new TypeToken<List<CartDefinition>>() {
            }.getType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return carts;
    }
}
