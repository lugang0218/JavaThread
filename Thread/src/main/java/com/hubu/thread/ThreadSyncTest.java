package com.hubu.thread;


import java.util.ArrayList;
import java.util.List;

/**
 * 线程同步测试
 */
public class ThreadSyncTest {
    static List<Integer> list=new ArrayList<>();
    public static void main(String[] args) {
        Thread t=new Thread(()->{
            for(int i=0;i<10000000;i++){
                list.add((i+1));
            }
        });
        t.start();
        try {
            /**
             *
             *
             * 等待线程返回
             */
            t.join();
            for(int i=0;i<100000000;i++){
                System.out.println(list.get(i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
