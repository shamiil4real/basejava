package com.basejava.webapp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {

    public static void main(String[] args) throws InterruptedException {
        Account acc1 = new Account();
        Account acc2 = new Account();

        final Lock lock1 = new ReentrantLock();
        final Lock lock2 = new ReentrantLock();

        Thread thread1 = new Thread(() -> doTransfer(acc1, acc2, lock1, lock2));
        Thread thread2 = new Thread(() -> doTransfer(acc2, acc1, lock2, lock1));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Баланс 1го аккаунта: " + acc1.balance + ", Баланс 2го аккаунта: " + acc2.balance);
        System.out.println("Общая сумма: " + (acc1.balance + acc2.balance));
    }

    static class Account {

        private int balance = 10000;

        public void deposit(int amount) {
            balance += amount;
        }

        public void withdraw(int amount) {
            balance -= amount;
        }

        public static void transfer(Account acc1, Account acc2, int amount) {
            acc1.withdraw(amount);
            acc2.deposit(amount);
        }
    }

    private static void doTransfer(Account acc1, Account acc2, Lock lock1, Lock lock2) {
        for (int i = 0; i < 100; i++) {
            lock1.lock();
            lock2.lock();
            try {
                Account.transfer(acc1, acc2, (int) (Math.random() * 500));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }
}
