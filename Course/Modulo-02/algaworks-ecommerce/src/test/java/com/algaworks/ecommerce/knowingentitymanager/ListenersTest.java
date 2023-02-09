package com.algaworks.ecommerce.knowingentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Client;
import com.algaworks.ecommerce.model.Product;
import com.algaworks.ecommerce.model.PurchaseOrder;
import com.algaworks.ecommerce.model.StatusOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ListenersTest extends EntityManagerTest {
    @Test
    public void startCallbacks() {
        Client client = entityManager.find(Client.class, 1);
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setClient(client);
        purchaseOrder.setStatus(StatusOrder.WAITING);

        entityManager.getTransaction().begin();
        entityManager.persist(purchaseOrder);
        entityManager.flush();

        purchaseOrder.setStatus(StatusOrder.PAID);
        entityManager.getTransaction().commit();

        entityManager.clear();
        PurchaseOrder verifyPurchaseorder = entityManager.find(PurchaseOrder.class, purchaseOrder.getId());
        assertNotNull(verifyPurchaseorder.getCreationDate());
        assertNotNull(verifyPurchaseorder.getLastUpdateDate());
    }

    @Test
    public void loadingEntities() {
        Product product = entityManager.find(Product.class, 1);
        PurchaseOrder purchaseOrder = entityManager.find(PurchaseOrder.class, 1);
    }
}
