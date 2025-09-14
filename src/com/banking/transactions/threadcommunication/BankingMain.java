package com.banking.transactions.threadcommunication;

public class BankingMain {
    public static void main(String[] args) {
        DepositMoney dm =new DepositMoney();
        WithdrawMoney wm = new WithdrawMoney(dm);

        Thread depositThread = new Thread(() ->{
            for (int i=0; i<=3; i++){
                dm.deposit(100);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        },"Thread-Deposit");


    Thread withdrawThread = new Thread(() -> {
        for(int i = 0; i<=3; i++){
            wm.withdraw(100);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    },"Withdraw thread");

    depositThread.start();
    withdrawThread.start();
        try {
            depositThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            withdrawThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Final Balance "+ dm.getBalance());

}


}