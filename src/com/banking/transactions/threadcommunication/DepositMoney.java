package com.banking.transactions.threadcommunication;

import com.banking.transactions.usingsynchronised.DepositInterface;

public class DepositMoney implements DepositInterface {

private int balance =500;

    public synchronized int getBalance() {
        return balance;
    }

    public synchronized void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public int deposit(int amount) {
        synchronized (this){
        System.out.println(Thread.currentThread().getName()+" Performing Deposit");
        balance=balance+amount;
        System.out.println(Thread.currentThread().getName()+" Balance after Performing Deposit: "+balance);
        notifyAll();
        return balance;
    }}
}
