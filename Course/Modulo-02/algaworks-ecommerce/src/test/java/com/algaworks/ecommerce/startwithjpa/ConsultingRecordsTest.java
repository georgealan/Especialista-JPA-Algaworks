package com.algaworks.ecommerce.startwithjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Product;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConsultingRecordsTest extends EntityManagerTest {

    @Test
    public void findById() {
//        Product product = entityManager.find(Product.class, 1);
        Product product = entityManager.getReference(Product.class, 1);
        assertNotNull(product);
        assertEquals("Kindle", product.getName());
    }

    @Test
    public void updateReference() {
        Product product = entityManager.find(Product.class, 1);
        product.setName("Iphone");

        entityManager.refresh(product);

        assertEquals("Kindle", product.getName());
    }
}
