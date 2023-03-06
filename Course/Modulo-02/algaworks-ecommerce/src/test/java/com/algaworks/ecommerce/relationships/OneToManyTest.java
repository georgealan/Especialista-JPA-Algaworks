package com.algaworks.ecommerce.relationships;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OneToManyTest extends EntityManagerTest {
    @Test
    public void verifyOneToMany() {
        Client client = entityManager.find(Client.class, 1);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setStatus(StatusOrder.PAID);
        purchaseOrder.setCreationDate(LocalDateTime.now());
        purchaseOrder.setTotal(BigDecimal.TEN);
        purchaseOrder.setClient(client);

        entityManager.getTransaction().begin();
        entityManager.persist(purchaseOrder);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Client verifyClient = entityManager.find(Client.class, client.getId());
        assertFalse(verifyClient.getPurchaseOrders().isEmpty());
    }

    @Test
    public void verifyOrderedItemOneToMany() {
        Product product = entityManager.find(Product.class, 3);
        Client client = entityManager.find(Client.class, 2);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setClient(client);
        purchaseOrder.setCreationDate(LocalDateTime.now());
        purchaseOrder.setStatus(StatusOrder.WAITING);
        purchaseOrder.setTotal(BigDecimal.TEN);

        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setId(new OrderedItemId());
        orderedItem.setProductPrice(product.getPrice());
        orderedItem.setQuantity(20);
        orderedItem.setPurchaseOrder(purchaseOrder);
        orderedItem.setProduct(product);

        entityManager.getTransaction().begin();
        entityManager.persist(purchaseOrder);
        entityManager.persist(orderedItem);
        entityManager.getTransaction().commit();
        entityManager.clear();

        PurchaseOrder verifyPurchaseOrder = entityManager.find(PurchaseOrder.class, purchaseOrder.getId());
        assertNotNull(verifyPurchaseOrder);
        assertFalse(verifyPurchaseOrder.getOrderedItems().isEmpty());
    }

    @Test
    public void findOrderedItem() {
        OrderedItem orderedItem = entityManager.find(OrderedItem.class, new OrderedItemId(1, 1));
        assertNotNull(orderedItem);
    }
}
