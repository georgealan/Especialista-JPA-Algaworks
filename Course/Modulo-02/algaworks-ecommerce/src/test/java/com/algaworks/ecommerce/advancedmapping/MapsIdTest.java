package com.algaworks.ecommerce.advancedmapping;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Invoice;
import com.algaworks.ecommerce.model.PurchaseOrder;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class MapsIdTest extends EntityManagerTest {
    @Test
    public void insertPayment() {
        PurchaseOrder purchaseOrder = entityManager.find(PurchaseOrder.class, 1);

        Invoice invoice = new Invoice();
        invoice.setPurchaseOrder(purchaseOrder);
        invoice.setIssueDate(new Date());
        invoice.setXml("<xml/>");

        entityManager.getTransaction().begin();
        entityManager.persist(invoice);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Invoice verifyInvoice = entityManager.find(Invoice.class, invoice.getId());
        assertNotNull(verifyInvoice);
        assertEquals(purchaseOrder.getId(), verifyInvoice.getId());
    }
}
