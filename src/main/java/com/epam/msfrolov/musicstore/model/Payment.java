package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

public class Payment extends BaseEntity {
    User seller;
    User buyer;
    Money sum;
    boolean done;

    private Payment(User seller, User buyer, Money sum) {
        this.seller = seller;
        this.buyer = buyer;
        this.sum = sum;
        buyer.getAccount().writeOff(sum);
        seller.getAccount().accrual(sum);
        this.done = true;
    }

    public static Payment conduct(User seller, User buyer, Money sum) {
        return new Payment(seller, buyer, sum);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id" +
                ", seller=" + seller +
                ", buyer=" + buyer +
                ", sum=" + sum +
                ", done=" + done +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Payment payment = (Payment) o;

        if (done != payment.done) return false;
        if (buyer != null ? !buyer.equals(payment.buyer) : payment.buyer != null) return false;
        return sum != null ? sum.equals(payment.sum) : payment.sum == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (seller != null ? seller.hashCode() : 0);
        result = 31 * result + (buyer != null ? buyer.hashCode() : 0);
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        result = 31 * result + (done ? 1 : 0);
        return result;
    }
}