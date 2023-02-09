package com.algaworks.ecommerce.knowingentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.PurchaseOrder;
import com.algaworks.ecommerce.model.StatusOrder;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ManageTransactionsTest extends EntityManagerTest {
    @Test
    public void openCloseCancelTransaction() {
        try {
            entityManager.getTransaction().begin();
            Exception exception = assertThrows(RuntimeException.class, () -> bussinessRule());
            entityManager.getTransaction().commit();

            assertEquals("Order dont paid yet!", exception.getMessage());
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    private void bussinessRule() {
        PurchaseOrder purchaseOrder = entityManager.find(PurchaseOrder.class, 1);
        purchaseOrder.setStatus(StatusOrder.PAID);

        if (purchaseOrder.getPayment() == null) {
            throw new RuntimeException("Order dont paid yet!");
        }
    }
}
