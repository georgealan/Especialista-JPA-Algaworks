package com.algaworks.ecommerce.relationships;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Invoice;
import com.algaworks.ecommerce.model.PaymentCard;
import com.algaworks.ecommerce.model.PaymentStatus;
import com.algaworks.ecommerce.model.PurchaseOrder;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class OneToOneTest extends EntityManagerTest {
    @Test
    public void verifyRelationship() {
        PurchaseOrder purchaseOrder = entityManager.find(PurchaseOrder.class, 1);
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setNumber("1234");
        paymentCard.setStatus(PaymentStatus.PROCESSING);
        paymentCard.setPurchaseOrder(purchaseOrder);

        entityManager.getTransaction().begin();
        entityManager.persist(paymentCard);
        entityManager.getTransaction().commit();
        entityManager.clear();

        PurchaseOrder verifyPurchase = entityManager.find(PurchaseOrder.class, purchaseOrder.getId());
        assertNotNull(verifyPurchase.getPaymentCard());
    }
    @Test
    public void verifyInvoiceRelationship() {
        PurchaseOrder purchaseOrder = entityManager.find(PurchaseOrder.class, 1);
        Invoice invoice = new Invoice();
        invoice.setXml("Test");
        invoice.setIssueDate(new Date());
        invoice.setPurchaseOrder(purchaseOrder);

        entityManager.getTransaction().begin();
        entityManager.persist(invoice);
        entityManager.getTransaction().commit();
        entityManager.clear();

        PurchaseOrder verifyPurchase = entityManager.find(PurchaseOrder.class, purchaseOrder.getId());
        assertNotNull(verifyPurchase.getInvoice());
    }
}
