# Java线程状态
### NEW
调用Java线程api创建的线程对象还没有与底层的操作系统相关联

对应操作系统的初始状态
### RUNNABLE

线程调用start之后的状态就是RUNNABLE状态,该状态对应操作系统里面的
可运行状态，运行状态，读取IO等对应的阻塞状态，Java中统一为RUNNABLE状态
### WAITING
线程调用wait，join等api
### TIME_WAITING
线程调用sleep方法
### BLOCKED
当多个线程获取锁的时候，没有获取到锁的线程的状态