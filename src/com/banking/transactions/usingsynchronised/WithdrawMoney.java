package com.banking.transactions.usingsynchronised;

public class WithdrawMoney implements Runnable,WithdrawInterface{
 private DepositMoney dm;

    public WithdrawMoney(DepositMoney dm) {
        this.dm = dm;
    }

    @Override
    public  int withdraw(int amount) {
        synchronized (dm) {
            System.out.println(Thread.currentThread().getName() + " Performing WITHDRAWL");

            int balance = dm.getBalance();
            if (balance < amount) {
                System.out.println("Insufficient balance");
                return 0;
            } else {
                balance = balance - amount;
                dm.setBalance(balance);
                System.out.println(balance);
            }
            return balance;
        }
    }

    @Override
    public void run() {
    withdraw(100);
    }


}
