package com.hubu.thread;
public class WaitTest implements Runnable {
    private static Object lock=new Object();
    public static void main(String[] args) {
        WaitTest task=new WaitTest();
        int threadCount=10;
        Thread array[]=new Thread[threadCount];
        for(int i=0;i<threadCount;i++){
            array[i]=new Thread(task,"t"+(i+1));
        }
        /**
         * 同时开启10个线程去抢锁，抢到之后进入睡眠
         */
        for(int i=0;i<threadCount;i++){
            array[i].start();
        }
        /**
         *
         * 等待10个线程都执行完毕在向下执行
         */
        for(int i=0;i<threadCount;i++){
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
         * 让多个线程来抢这把锁，谁抢到就将谁wait住，wait必须要结合使用，否则会抛异常
         *
         *
         *
         * 调用wait,抢到该锁的线程会释放锁，其他线程可以拿到这把锁
         */

        synchronized (lock){
            try {
                System.out.println(Thread.currentThread().getName()+"即将wait");
                /**
                 * 让进入lock监视器的线程到lock对象中的waitSet等待
                 */
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
