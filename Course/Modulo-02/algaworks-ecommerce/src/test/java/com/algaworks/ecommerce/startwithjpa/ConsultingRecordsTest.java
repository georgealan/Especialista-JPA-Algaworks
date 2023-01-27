package com.algaworks.ecommerce.startwithjpa;

import com.algaworks.ecommerce.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConsultingRecordsTest {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence
                .createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterAll
    public static void tearDownAfterClass() {
        entityManagerFactory.close();
    }

    @BeforeEach
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void tearDown() {
        entityManager.close();
    }

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
