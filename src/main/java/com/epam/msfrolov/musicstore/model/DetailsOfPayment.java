package com.epam.msfrolov.musicstore.model;

public enum DetailsOfPayment {
    PAY_PER_TRACK {
        @Override
        public String toString() {
            return "pay per track";
        }
    },
    ADD_BALANCE {
        @Override
        public String toString() {
            return "add balance";
        }
    },
    LIFTING_OF_BALANCE {
        @Override
        public String toString() {
            return "lifting of balance";
        }
    }
}
