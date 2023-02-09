package com.algaworks.ecommerce.service;

import com.algaworks.ecommerce.model.PurchaseOrder;

public class InvoiceService {
    public void generateInvoice(PurchaseOrder purchaseOrder) {
        System.out.println("Generate invoice for order " + purchaseOrder.getId() + ".");
    }
}
