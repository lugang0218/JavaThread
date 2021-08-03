package com.hubu.thread;


/**
 *
 *
 *
 * 测试Sleep
 */
class MyLock{
}
public class SleepTest implements Runnable {
    private static MyLock lock=new MyLock();
    public static void main(String[] args) {
        SleepTest task=new SleepTest();
        Thread array[]=new Thread[10];
        for(int i=0;i<10;i++){
            array[i]=new Thread(task,"t"+(i+1));
        }
        /**
         * 同时开启10个线程去抢锁，抢到之后进入睡眠
         */
        for(int i=0;i<10;i++){
            array[i].start();
        }
        /**
         *
         * 等待10个线程都执行完毕在向下执行
         */
        for(int i=0;i<10;i++){
            try {
                array[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     *
     * 任务函数，让外面的多个线程来工作
     */
    @Override
    public void run() {


        /**
         * 让多个线程来抢这把锁，谁抢到就让谁睡眠10秒钟
         */
        synchronized (lock){
            System.out.println(Thread.currentThread().getName()+"抢到锁，即将睡眠");

            try {
                Thread.sleep(10000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
