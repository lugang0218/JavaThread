package com.hubu.thread.stage;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
/**
 * 两阶段等待模式
 *
 */
@Slf4j(topic = "c.")
public class MonitorThead {
    private Thread monitor;
    public void start(){
        init();
        monitor.start();
    }
    public void init(){
        if(monitor==null){
            monitor=new Thread(){
                @Override
                public void run() {
                    while(true){
                        if(Thread.currentThread().isInterrupted()){
                            System.out.println("处理一些事情");
                            break;
                        }
                        try {
                            /**
                             *
                             *
                             * 如果是在睡眠过程中被打断，会抛出异常，打断标记会被设置位false，所以需要重新打断
                             */

                            log.info("监控线程开始监控");
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            /**
                             * 重新设置打断标记位
                             */
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            };
        }
    }
    public void stop(){
        monitor.interrupt();
    }
    public static void main(String[] args) {
        MonitorThead m=new MonitorThead();
        m.start();
        try {
            TimeUnit.SECONDS.sleep(1);
            m.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
