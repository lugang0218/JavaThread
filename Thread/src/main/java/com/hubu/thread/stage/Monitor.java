package com.hubu.thread.stage;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.TimeUnit;
@Slf4j(topic = "c.")
public class Monitor{
    private Thread monitorThread;
    public void monit(){
        Runnable task=()->{
            while(true){
                if(monitorThread.isInterrupted()){
                    log.info("线程被打断。。。。。");
                    log.info("处理一些工作");
                    break;
                }
                /**
                 * 模拟监控业务
                 */
                System.out.println("线程开始监控");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    log.info("线程睡眠两秒");
                } catch (InterruptedException e) {
                    log.info("睡眠过程中被打断");
                    e.printStackTrace();
                    /**
                     * 重新设置打断标记位
                     */
                    monitorThread.interrupt();
                }
            }
        };

        monitorThread=new Thread(task);
        monitorThread.start();
    }
    /**
     * 结束线程监控
     */
    public void endMonit(){
        monitorThread.interrupt();
    }
    public static void main(String[] args) {
        Monitor monitor=new Monitor();
        monitor.monit();
        try {
            TimeUnit.SECONDS.sleep(1);
            monitor.endMonit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
