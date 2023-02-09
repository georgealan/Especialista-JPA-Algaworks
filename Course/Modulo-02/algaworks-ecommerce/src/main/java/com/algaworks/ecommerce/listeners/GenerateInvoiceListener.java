package com.algaworks.ecommerce.listeners;

import com.algaworks.ecommerce.model.PurchaseOrder;
import com.algaworks.ecommerce.service.InvoiceService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class GenerateInvoiceListener {
    private InvoiceService invoiceService = new InvoiceService();

    @PrePersist
    @PreUpdate
    public void generateInvoice(PurchaseOrder purchaseOrder) {
        if (purchaseOrder.isPaid() && purchaseOrder.getInvoice() == null) {
            invoiceService.generateInvoice(purchaseOrder);
        }
    }
}
