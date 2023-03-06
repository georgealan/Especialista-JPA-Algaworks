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
        entityManager.getTransaction().begin();

        Client client = entityManager.find(Client.class, 2);
        Product product = entityManager.find(Product.class, 3);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setStatus(StatusOrder.WAITING);
        purchaseOrder.setCreationDate(LocalDateTime.now());
        purchaseOrder.setTotal(BigDecimal.TEN);
        purchaseOrder.setClient(client);

        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setId(new OrderedItemId());
        orderedItem.setProductPrice(product.getPrice());
        orderedItem.setQuantity(20);
        orderedItem.setPurchaseOrder(purchaseOrder);
        orderedItem.setProduct(product);

        entityManager.persist(orderedItem);
        entityManager.persist(purchaseOrder);
        entityManager.getTransaction().commit();
        entityManager.clear();

        OrderedItem verifyOrderedItem = entityManager.find(OrderedItem.class, new OrderedItemId(purchaseOrder.getId(), product.getId()));
        assertNotNull(verifyOrderedItem.getPurchaseOrder());
        assertNotNull(verifyOrderedItem.getProduct());
        assertEquals("José Carlos", verifyOrderedItem.getPurchaseOrder().getClient().getName());
        assertEquals("Go Pro Hero", verifyOrderedItem.getProduct().getName());
    }
}
