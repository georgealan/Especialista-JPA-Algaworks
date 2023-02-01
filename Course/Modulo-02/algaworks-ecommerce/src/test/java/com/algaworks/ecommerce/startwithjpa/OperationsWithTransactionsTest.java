package com.algaworks.ecommerce.startwithjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class OperationsWithTransactionsTest extends EntityManagerTest {
    @Test
    public void stopDatabaseOperationWithDetach() {
        Product product = entityManager.find(Product.class, 1);
        entityManager.detach(product);

        entityManager.getTransaction().begin();
        product.setName("Mouse Multilaser");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Product verifyProduct = entityManager.find(Product.class, product.getId());
        assertEquals("Kindle", verifyProduct.getName());
    }

    @Test
    public void insertFirstObject() {
        Product product = new Product();

        product.setName("Canon");
        product.setDescription("The best screen for your shots");
        product.setPrice(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    @Test
    public void removeObject() {
        Product product = entityManager.find(Product.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(product);
        entityManager.getTransaction().commit();

        Product verifyProduct = entityManager.find(Product.class, 3);
        assertNull(verifyProduct);
    }

    @Test
    public void updateObject() {
        Product product = new Product();

        product.setName("Kindle Paperwhite");
        product.setDescription("The new Kindle generation");
        product.setPrice(new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();

        entityManager.clear(); // For clear entityManager memory to get the last object persisted.

        Product verifyProduct = entityManager.find(Product.class, product.getId());
        assertNotNull(verifyProduct);
        assertEquals("Kindle Paperwhite", verifyProduct.getName());
    }

    @Test
    public void updateManagedObject() {
        Product product = entityManager.find(Product.class, 1);

        entityManager.getTransaction().begin();
        product.setName("Kindle Paperwhite 10 Generation");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Product verifyProduct = entityManager.find(Product.class, product.getId());
        assertEquals("Kindle Paperwhite 10 Generation", verifyProduct.getName());
    }

    @Test
    public void insertWithMerge() {
        Product product = new Product();

        product.setName("Notebook MSI GT70 2PC");
        product.setDescription("The best notebook ever");
        product.setPrice(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Product verifyProduct = entityManager.find(Product.class, product.getId());
        assertNotNull(verifyProduct);
    }

}
