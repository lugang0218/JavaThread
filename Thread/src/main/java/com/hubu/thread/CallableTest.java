package com.hubu.thread;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
public class CallableTest implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "hello world";
    }
    public static void main(String[] args) {
        FutureTask<String> task=new FutureTask<>(new CallableTest());
        Thread t=new Thread(task);
        t.start();
        try {
            String result=task.get();
            System.out.println("result="+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
