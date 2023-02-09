package com.algaworks.ecommerce.relationships;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ManyToOneTest extends EntityManagerTest {
    @Test
    public void verifyRelationship() {
        Client client = entityManager.find(Client.class, 1);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setStatus(StatusOrder.WAITING);
        purchaseOrder.setTotal(BigDecimal.TEN);
        purchaseOrder.setClient(client);

        entityManager.getTransaction().begin();
        entityManager.persist(purchaseOrder);
        entityManager.getTransaction().commit();
        entityManager.clear();

        PurchaseOrder verifyPurchaseOrder = entityManager.find(PurchaseOrder.class, purchaseOrder.getId());
        assertNotNull(verifyPurchaseOrder.getClient());
        assertEquals("Ayrton Senna", verifyPurchaseOrder.getClient().getName());
    }

    @Test
    public void verifyOrderedItem() {
        Product product = entityManager.find(Product.class, 3);
        Client client = entityManager.find(Client.class, 2);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setStatus(StatusOrder.WAITING);
        purchaseOrder.setTotal(BigDecimal.TEN);
        purchaseOrder.setClient(client);

        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setProduct(product);
        orderedItem.setPurchaseOrder(purchaseOrder);
        orderedItem.setQuantity(20);
        orderedItem.setProductPrice(BigDecimal.TEN);

        entityManager.getTransaction().begin();
        entityManager.persist(purchaseOrder);
        entityManager.persist(orderedItem);
        entityManager.getTransaction().commit();
        entityManager.clear();

        OrderedItem verifyOrderedItem = entityManager.find(OrderedItem.class, orderedItem.getId());
        assertNotNull(verifyOrderedItem.getProduct());
        assertNotNull(verifyOrderedItem.getPurchaseOrder());
        assertEquals("Go Pro Hero", verifyOrderedItem.getProduct().getName());
        assertEquals("José Carlos", verifyOrderedItem.getPurchaseOrder().getClient().getName());
    }
}
