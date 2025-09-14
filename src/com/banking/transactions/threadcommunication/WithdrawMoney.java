package com.banking.transactions.threadcommunication;

import com.banking.transactions.usingsynchronised.WithdrawInterface;

public class WithdrawMoney implements WithdrawInterface {
    private DepositMoney dm;

    public WithdrawMoney(DepositMoney dm) {
        this.dm = dm;
    }

    @Override
    public int withdraw(int amount) {
       synchronized (dm){
           while(dm.getBalance()< amount){
               System.out.println(Thread.currentThread().getName()+ " Waiting - Insufficient balance");
               try {
                   dm.wait();
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
                   System.out.println("Thread interrupted during withdrawl");
                   return dm.getBalance();
               }
           }
           System.out.println(Thread.currentThread().getName()+" Performing withdrawl");
           int balance = dm.getBalance()-amount;
           dm.setBalance(balance);
           System.out.println(Thread.currentThread().getName()+" Balance after Performing withdrawl: "+dm.getBalance());

           return balance;
       }
    }
}
