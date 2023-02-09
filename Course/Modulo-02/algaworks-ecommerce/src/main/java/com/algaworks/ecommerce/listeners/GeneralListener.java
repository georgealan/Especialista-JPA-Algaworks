package com.algaworks.ecommerce.listeners;

import jakarta.persistence.PostLoad;

public class GeneralListener {
    @PostLoad
    public void logLoading(Object obj) {
        System.out.println("Entity " + obj.getClass().getSimpleName() + " was loaded.");
    }
}
