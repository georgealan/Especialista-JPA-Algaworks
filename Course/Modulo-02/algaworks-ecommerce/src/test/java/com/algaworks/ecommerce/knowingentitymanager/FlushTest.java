package com.algaworks.ecommerce.knowingentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import static org.junit.jupiter.api.Assertions.*;

import com.algaworks.ecommerce.model.PurchaseOrder;
import com.algaworks.ecommerce.model.StatusOrder;
import org.junit.jupiter.api.Test;

public class FlushTest extends EntityManagerTest {
    @Test
    public void callFlush() {
        try {
            entityManager.getTransaction().begin();

            Exception exception = assertThrows(RuntimeException.class, () -> bussinessRule());
            assertEquals("Order dont paid yet!", exception.getMessage());

            entityManager.flush();
            /*
            O método flush() serve para forçar o JPA enviar updates para o banco de dados, ele obriga o JPA a pegar
            tudo o que está na memoria e ainda não está sincronizado com o banco e sincronizar com o banco.
             */

            // Uma consulta obriga o JPA a sincronizar o que ele tem na memória (sem usar o flush explicitamente).
            /*PurchaseOrder paidOrder = entityManager
                    .createQuery("select p from PurchaseOrder p where p.id = 1", PurchaseOrder.class)
                    .getSingleResult();
            assertEquals(purchaseOrder.getStatus(), paidOrder.getStatus());*/

            entityManager.getTransaction().commit();

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
