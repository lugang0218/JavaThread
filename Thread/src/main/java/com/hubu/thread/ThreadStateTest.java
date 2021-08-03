package com.hubu.thread;
import java.util.concurrent.TimeUnit;

public class ThreadStateTest {

    private static Object waitObject=new Object();


    private static Object sleepObject=new Object();

    public static  void main(String[] args) {
        /**
         * NEW状态 对应操作系统的初始状态
         */
        Thread t1=new Thread(()->{
            System.out.println("t1");
        });
        System.out.println(t1.getState());


        /**
         * Runnable状态 对应操作系统的可运行状态 运行状态 阻塞(io操作)状态
         */
        Thread t2=new Thread(()->{
            while(true){
                //System.out.println("hello world");
            }
        });
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(t2.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /**
         *
         * Java特有的状态 TIMED_WAITING
         */
        Thread t3=new Thread(()->{
            try {
                TimeUnit.DAYS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.start();

        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(t3.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /**
         *
         * Java特有的状态
         * WAITING
         */
        Thread t4=new Thread(()->{
            synchronized (waitObject){
                try {
                    waitObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t4.start();
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(t4.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         *
         * Java特有的状态
         * BLOCKED
         */


        Thread t5=new Thread(()->{
            synchronized (sleepObject){
                try {
                    TimeUnit.DAYS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



        Thread t6=new Thread(()->{
            synchronized (sleepObject){
                try {
                    TimeUnit.DAYS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t5.start();

        try {
            TimeUnit.SECONDS.sleep(1);
            t6.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(t6.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
