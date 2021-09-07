package com.hubu.thread.wait;

public class WaitDemo {
    static class Lock{
    }
    private static Lock lock=new Lock();
    private  static int count=0;
    public static void main(String[] args) {
        print();
    }
    /**
     *
     *
     * 每一个Java对象都可以作为一把锁,该锁对象关联了一个Monitor ，
     * 该Monitor持有WaitSet(只有调用lock.wait方法的线程才可以将该线程放入WaitSet)
     * EntryList(竞争lock失败的线程将会lock锁对象的EntryList中)
     * lock.wait之后将会释放当前线程持有的锁lock,此时使用lock.notify将会通知EntryList中等待的线程竞争lock锁
     *
     *
     */
    public static void print(){
        Thread t1=new Thread(()->{
            synchronized (lock){
                while(true){
                    if(count%2==0){
                        System.out.println(count);
                        count++;
                        //通知打印奇数的线程开始打印
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        //通知打印奇数的线程开始打印
                        lock.notify();
                        try {
                            //自己wait住
                            lock.wait();
                            System.out.println("线程醒来");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread t2=new Thread(()->{
            synchronized (lock){
                while(true){
                    if(count%2==1){
                        System.out.println(count);
                        count++;
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        t1.start();
        t2.start();
    }
}
