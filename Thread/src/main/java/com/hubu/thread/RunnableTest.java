package com.hubu.thread;


/**
 *
 *
 * 线程基本测试 测试Runnable接口的使用
 */
public class RunnableTest implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test Runnable");
    }
    public static void main(String[] args) {
        Thread t=new Thread(new RunnableTest());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
