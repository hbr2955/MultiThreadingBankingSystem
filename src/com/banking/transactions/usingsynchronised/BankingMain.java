package com.banking.transactions.usingsynchronised;

public class BankingMain {
    public static void main(String[] args) throws InterruptedException {
        DepositMoney dm =new DepositMoney();
        WithdrawMoney wm = new WithdrawMoney(dm);
        Thread t1 = new Thread(dm,"Thread1");
        Thread t2 = new Thread(dm,"Thread2");
        Thread t3 = new Thread(wm,"Thread3");
        Thread t4 = new Thread(wm,"Thread4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

    }
}
