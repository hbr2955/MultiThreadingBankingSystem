package com.banking.transactions.executor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorFrameWork
{
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        Callable<Long> callable1=() ->  factorial(2);
        Callable<Long> callable2=() ->  factorial(3);
        Callable<Long> callable3=() ->  factorial(4);
        List<Callable<Long>> callList = List.of(callable1,callable2,callable3);
        List<Future<Long>> factorialList = executorService.invokeAll(callList);
        System.out.println(";;;" +factorialList);
        for (int i = 0; i <= 9; i++) {
            int a = i;



            // CALLABLE as parameter to submit
            /*Future<Long> d=  executorService.submit(() ->  factorial(a));*/

            // RUNNABLE as parameter to submit
          /*Future<?> d=  executorService.submit(() -> {
                long result= factorial(a);
                System.out.println(result);
            });*/


            // RUNNABLE as parameter to submit <T> Future<T> submit(Runnable task, T result);
            Future<Integer> d = executorService.submit(() -> {
                long result = factorial(a);
                System.out.println(result);
            }, 4);

        }
        executorService.shutdown();


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