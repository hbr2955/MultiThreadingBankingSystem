package com.banking.transactions.usingsynchronised;

public class DepositMoney implements Runnable,DepositInterface{

    private int balance =500;

    public synchronized int getBalance() {
        return balance;
    }

    public synchronized void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public  int  deposit(int amount) {
        synchronized(this) {
            System.out.println(Thread.currentThread().getName() + " Performing DEPOSIT");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            balance = balance + amount;
            System.out.println(Thread.currentThread().getName() + " balance " + balance);
            return balance;
        }
    }
    @Override
    public void run() {
       deposit(100);
    }

    public static void main(String[] args) {
        DepositMoney dm =new DepositMoney();
        Thread t1 = new Thread(dm,"Thread1");
        Thread t2 = new Thread(dm,"Thread2");

        t1.start();

        t2.start();
    }
}
