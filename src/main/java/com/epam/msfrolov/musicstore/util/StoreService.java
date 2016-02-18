package com.epam.msfrolov.musicstore.util;

import com.epam.msfrolov.musicstore.model.*;
import org.joda.money.Money;

public class StoreService {
    public static boolean buyTrack(Track track, User user) {
        if (!user.getAccount().canSpend(track.getPrice())) {
            return false;
        }
        Payment payment = Payment.conduct(user, User.ADMIN, track.getPrice(), DetailsOfPayment.PAY_PER_TRACK);
        user.getBoughtTracks().add(track);
        System.out.println(payment);
        return payment.isDone();
    }

    public static boolean buyAlbum(Album album, User user) {
        if (!user.getAccount().canSpend(album.getPrice())) {
            return false;
        }
        Payment payment = Payment.conduct(user, User.ADMIN, album.getPrice(), DetailsOfPayment.PAY_PER_TRACK);
        for (Track track:album) {
            user.getBoughtTracks().add(track);
        }
        System.out.println(payment);
        return payment.isDone();
    }

    public static boolean accrualMoneyInAccount(User user, Money value) {
        Payment payment = Payment.conduct(User.ADMIN, user, value, DetailsOfPayment.ADD_BALANCE);
        return payment.isDone();
    }

    public static boolean takeOffMoneyInAccount(User user, Money value) {
        if (!user.getAccount().canSpend(value)) return false;
        Payment payment = Payment.conduct(user, User.ADMIN, value, DetailsOfPayment.LIFTING_OF_BALANCE);
        return payment.isDone();
    }
}