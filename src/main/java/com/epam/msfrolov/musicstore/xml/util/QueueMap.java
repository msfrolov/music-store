package com.epam.msfrolov.musicstore.xml.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map.Entry;

public class QueueMap<T1, T2> {

    Deque<Entry<T1, T2>> deque = new ArrayDeque<>();

    public Entry<T1, T2> push(T1 t1, T2 t2) {
        Entry<T1, T2> node = new Node<>(t1, t2);
        deque.addLast(node);
        return node;
    }

    public Entry<T1, T2> pop() {
        return deque.pollLast();
    }


    public Entry<T1, T2> peek() {
        return deque.peekLast();
    }

    public T1 peekKey() {
        Entry<T1, T2> node = deque.peekLast();
        return node.getKey();
    }

    public T2 peekValue() {
        Entry<T1, T2> node = deque.peekLast();
        return node.getValue();
    }


    private class Node<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V t2) {
            this.value = t2;
            return this.value;
        }
    }
}
