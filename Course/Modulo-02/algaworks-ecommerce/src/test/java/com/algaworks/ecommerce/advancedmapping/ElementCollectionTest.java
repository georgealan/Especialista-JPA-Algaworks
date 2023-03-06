package com.algaworks.ecommerce.advancedmapping;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Product;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ElementCollectionTest extends EntityManagerTest {

    @Test
    public void applyTags() {
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1);
        product.setTags(Arrays.asList("ebook", "e-reader"));

        entityManager.getTransaction().commit();
        entityManager.clear();

        Product verifyProduct = entityManager.find(Product.class, product.getId());
        assertFalse(verifyProduct.getTags().isEmpty());
    }

}
