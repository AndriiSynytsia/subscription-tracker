package com.keyn_bello.subscription_tracker.entity;

public enum PaymentMethod {
    CREDIT_CARD("CREDIT_CARD"),
    DEBIT_CARD("DEBIT_CARD"),
    PAYPAL("PAYPAL"),
    APPLE_PAY("APPLE_PAY"),
    BANK_TRANSFER("BANK_TRANSFER");

    PaymentMethod(String payment) {
    }
}
