package com.algaworks.ecommerce.advancedmapping;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CompositeKeyTest extends EntityManagerTest {

    @Test
    public void saveItem() {
        entityManager.getTransaction().begin();

        Client client = entityManager.find(Client.class, 1);
        Product product = entityManager.find(Product.class, 1);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setClient(client);
        purchaseOrder.setCreationDate(LocalDateTime.now());
        purchaseOrder.setStatus(StatusOrder.WAITING);
        purchaseOrder.setTotal(product.getPrice());

        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setId(new OrderedItemId());
        orderedItem.setPurchaseOrder(purchaseOrder);
        orderedItem.setProduct(product);
        orderedItem.setProductPrice(product.getPrice());
        orderedItem.setQuantity(1);

        entityManager.persist(orderedItem);
        entityManager.persist(purchaseOrder);
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
