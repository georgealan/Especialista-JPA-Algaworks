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

        Product product = entityManager.find(Product.class, 3);
        Client client = entityManager.find(Client.class, 2);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCreationDate(LocalDateTime.now());
        purchaseOrder.setStatus(StatusOrder.WAITING);
        purchaseOrder.setTotal(BigDecimal.TEN);
        purchaseOrder.setClient(client);

        entityManager.persist(purchaseOrder);
        /*
        It may be that when executing the "persist" method, JPA already synchronizes with the base.
        But if that doesn't happen, the flush guarantee's synchronization.
         */
        entityManager.flush();

        OrderedItem orderedItem = new OrderedItem();
//        orderedItem.setPurchaseOrderId(purchaseOrder.getId());
//        orderedItem.setProductId(product.getId());
        orderedItem.setId(new OrderedItemId(product.getId(), purchaseOrder.getId()));
        orderedItem.setProductPrice(product.getPrice());
        orderedItem.setPurchaseOrder(purchaseOrder);
        orderedItem.setProduct(product);
        orderedItem.setQuantity(20);

        entityManager.persist(orderedItem);
        entityManager.getTransaction().commit();
        entityManager.clear();

        OrderedItem verifyOrderedItem = entityManager.find(OrderedItem.class, new OrderedItemId(purchaseOrder.getId(), product.getId()));
        assertNotNull(verifyOrderedItem.getProduct());
        assertNotNull(verifyOrderedItem.getPurchaseOrder());
        assertEquals("Go Pro Hero", verifyOrderedItem.getProduct().getName());
        assertEquals("José Carlos", verifyOrderedItem.getPurchaseOrder().getClient().getName());
    }
}
