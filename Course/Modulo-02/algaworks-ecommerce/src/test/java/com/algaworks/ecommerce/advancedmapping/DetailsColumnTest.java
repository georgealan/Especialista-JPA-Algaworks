package com.algaworks.ecommerce.advancedmapping;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Client;
import com.algaworks.ecommerce.model.Product;
import com.algaworks.ecommerce.model.PurchaseOrder;
import com.algaworks.ecommerce.model.StatusOrder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class DetailsColumnTest extends EntityManagerTest {

    @Test
    public void avoidInsertionLastUpdateDateFieldProduct() {
        Product product = new Product();
        product.setName("Notebook MSI");
        product.setDescription("Intel Core 12th");
        product.setPrice(BigDecimal.TEN);
        product.setCreationDate(LocalDateTime.now());
        product.setLastUpdateDate(LocalDateTime.now());

        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Product verifyProduct = entityManager.find(Product.class, product.getId());
        assertNotNull(verifyProduct.getCreationDate());
        assertNull(verifyProduct.getLastUpdateDate());
    }

    @Test
    public void avoidUpdateCreationDateFieldProduct() {
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1);
        product.setPrice(BigDecimal.ONE);
        product.setCreationDate(LocalDateTime.now());
        product.setLastUpdateDate(LocalDateTime.now());

        entityManager.getTransaction().commit();
        entityManager.clear();

        Product verifyProduct = entityManager.find(Product.class, product.getId());

        assertNotEquals(product.getCreationDate().truncatedTo(ChronoUnit.SECONDS),
                verifyProduct.getCreationDate().truncatedTo(ChronoUnit.SECONDS));

        assertEquals(product.getLastUpdateDate().truncatedTo(ChronoUnit.SECONDS),
                verifyProduct.getLastUpdateDate().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    public void avoidInsertionLastUpdateDateFieldPurchasedOrder() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        Client client = entityManager.find(Client.class, 1);
        purchaseOrder.setClient(client);
        purchaseOrder.setCreationDate(LocalDateTime.now());
        purchaseOrder.setLastUpdateDate(LocalDateTime.now());
        purchaseOrder.setTotal(BigDecimal.ONE);
        purchaseOrder.setStatus(StatusOrder.PAID);

        entityManager.getTransaction().begin();
        entityManager.persist(purchaseOrder);
        entityManager.getTransaction().commit();
        entityManager.clear();

        PurchaseOrder verifyPurchaseOrder = entityManager.find(PurchaseOrder.class, purchaseOrder.getId());
        assertNotNull(verifyPurchaseOrder.getCreationDate());
        assertNull(verifyPurchaseOrder.getLastUpdateDate());
    }

    @Test
    public void avoidUpdateCreationDateFieldPurchasedOrder() {
        entityManager.getTransaction().begin();

        PurchaseOrder purchaseOrder = entityManager.find(PurchaseOrder.class, 1);
        purchaseOrder.setCreationDate(LocalDateTime.now());
        purchaseOrder.setLastUpdateDate(LocalDateTime.now());

        entityManager.getTransaction().commit();
        entityManager.clear();

        PurchaseOrder verifyPurchaseOrder = entityManager.find(PurchaseOrder.class, purchaseOrder.getId());
        assertNotEquals(purchaseOrder.getCreationDate().truncatedTo(ChronoUnit.SECONDS),
                verifyPurchaseOrder.getCreationDate().truncatedTo(ChronoUnit.SECONDS));

        assertEquals(purchaseOrder.getLastUpdateDate().truncatedTo(ChronoUnit.SECONDS),
                verifyPurchaseOrder.getLastUpdateDate().truncatedTo(ChronoUnit.SECONDS));
    }
}
