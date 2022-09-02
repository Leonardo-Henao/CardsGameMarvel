package com.sofka.cardsgame.domain.values;

import co.com.sofka.domain.generic.Identity;

public class CartaMaestraId extends Identity {

    public CartaMaestraId() {
    }

    public CartaMaestraId(String id) {
        super(id);
    }

    public static CartaMaestraId of(String id) {
        return new CartaMaestraId(id);
    }
}
