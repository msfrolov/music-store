package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

public class Payment extends BaseEntity {
    User seller;
    User buyer;
    Money sum;
    boolean done;
    String details;

    private Payment(User recipient, User sender, Money sum, DetailsOfPayment details) {
        this.seller = recipient;
        this.buyer = sender;
        this.sum = sum;
        sender.getAccount().writeOff(sum);
        recipient.getAccount().accrual(sum);
        this.done = true;
    }

    public static Payment conduct(User recipient, User sender, Money sum, DetailsOfPayment details) {
        return new Payment(recipient, sender, sum, details);
    }

    public boolean isDone(){
        return done;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "recipient=" + seller +
                ", sender=" + buyer +
                ", sum=" + sum +
                ", done=" + done +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Payment payment = (Payment) o;

        return done == payment.done
                && (seller != null ? seller.equals(payment.seller) : payment.seller == null
                && (buyer != null ? buyer.equals(payment.buyer) : payment.buyer == null
                && (sum != null ? sum.equals(payment.sum) : payment.sum == null
                && (details != null ? details.equals(payment.details) : payment.details == null))));

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (seller != null ? seller.hashCode() : 0);
        result = 31 * result + (buyer != null ? buyer.hashCode() : 0);
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        result = 31 * result + (done ? 1 : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }
}