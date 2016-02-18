package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

public class Payment extends BaseEntity {
    User recipient;
    User sender;
    Money sum;
    boolean done;
    DetailsOfPayment details;

    private Payment(User sender, User recipient, Money sum, DetailsOfPayment details) {
        this.recipient = recipient;
        this.sender = sender;
        this.sum = sum;
        if (sender.getAccount() == null)
            sender.setAccount(new Account(sender));
        sender.getAccount().writeOff(sum);
        if (recipient.getAccount() == null)
            recipient.setAccount(new Account(recipient));
        recipient.getAccount().accrual(sum);
        this.details = details;
        this.done = true;
    }

    public static Payment conduct(User sender, User recipient, Money sum, DetailsOfPayment details) {
        Payment payment = new Payment(sender, recipient, sum, details);
        System.out.println(payment);
        return payment;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id " + getId() +
                ", sender=" + sender +
                ", recipient=" + recipient +
                ", sum=" + sum +
                ", done=" + done +
                ", details='" + details + '\'' +
                '}';
    }
}