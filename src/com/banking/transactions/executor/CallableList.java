package com.banking.transactions.executor;

import java.util.List;
import java.util.concurrent.*;

public class CallableList {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        Callable<Long> callable1 = () -> factorial(2);
        Callable<Long> callable2 = () -> factorial(3);
        Callable<Long> callable3 = () -> factorial(4);
        List<Callable<Long>> callList = List.of(callable1, callable2, callable3);
      List<Future<Long>> factorialList = executorService.invokeAll(callList);
        try {
            Long factorial1 = executorService.invokeAny(callList);
            System.out.println("INVOKE ANY: "+ factorial1);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        for (Future<Long> facto : factorialList) {
            try {
                System.out.println(facto.get());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdown();
        System.out.println("Hello");
    }
    private static long factorial(int n) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

}