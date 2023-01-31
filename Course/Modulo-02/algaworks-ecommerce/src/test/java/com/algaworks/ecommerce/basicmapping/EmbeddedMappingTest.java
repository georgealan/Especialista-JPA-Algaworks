package com.algaworks.ecommerce.basicmapping;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.PurchaseOrder;
import com.algaworks.ecommerce.model.OrderDeliveryAddress;
import com.algaworks.ecommerce.model.StatusOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EmbeddedMappingTest extends EntityManagerTest {
    @Test
    public void verifyEmbeddedObject() {
        OrderDeliveryAddress adress = new OrderDeliveryAddress();
        adress.setCep("08422-80");
        adress.setLogradouro("Rua dos pilaretes");
        adress.setNumero("123");
        adress.setBairro("centro");
        adress.setCidade("São Paulo");
        adress.setEstado("SP");
        adress.setComplemento("17");

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(1);
        purchaseOrder.setOrderDate(LocalDateTime.now());
        purchaseOrder.setStatus(StatusOrder.WAITING);
        purchaseOrder.setTotal(new BigDecimal(1000));
        purchaseOrder.setOrderDeliveryAddress(adress);

        entityManager.getTransaction().begin();
        entityManager.persist(purchaseOrder);
        entityManager.getTransaction().commit();
        entityManager.clear();

        PurchaseOrder verifyPurchaseOrder = entityManager.find(PurchaseOrder.class, purchaseOrder.getId());
        assertNotNull(verifyPurchaseOrder);
        assertNotNull(verifyPurchaseOrder.getOrderDeliveryAddress());
        assertNotNull(verifyPurchaseOrder.getOrderDeliveryAddress().getCep());
    }
}
