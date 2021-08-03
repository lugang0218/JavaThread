package com.hubu.thread;
/**
 * 线程基本测试
 *
 */
public class ThreadTest extends Thread{
    /**
     * 线程入口函数，执行逻辑
     */
    @Override
    public void run() {
        /**
         * Thread的sleep静态方法，线程开始睡眠，睡眠过程中不会释放锁
         */
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * 获取当前线程的名字
         */
        System.out.println(Thread.currentThread().getName());
        System.out.println("我的线程开始运行");
    }
    public static void main(String[] args) {
        /**
         * 创建一个线程对象
         */
        ThreadTest t1=new ThreadTest();
        /**
         * 开启线程，start方法表示线程开始运行，状态为就绪状态，线程具体的运行是由cpu来调度的
         */

        /**
         * 想要让新的线程运行任务，必须要调用start方法，不能直接调用run方法，否则还是在主线程里面执行的
         */
        t1.start();
        /**
         * 谁调用了线程的join方法，谁就要等待该线程执行完毕之后才能继续向下运行
         */
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("WorkThread执行完毕,主线成被唤醒执行");
    }
}
