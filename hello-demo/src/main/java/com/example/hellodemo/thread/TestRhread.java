package com.example.hellodemo.thread;

/**
 * @author libin
 * @date 2021年12月15日 16:06
 */
public class TestRhread extends Thread {

    //设置有100张票
    private static int count = 10;

    private static final Object lock = new Object();

    public TestRhread(String name) {
        super(name);
    }

    @Override
    public  void run() {
        while(true) {
            synchronized(lock) {//第一个线程来的时候会锁上，并拿走钥匙，第二个线程来的时候，发现被锁上，等待
                //当票大于0张的时候卖票
                if (count > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出第" + count + "卖票");
                    count--;
                }else{
                    System.out.println("抢光了");
                    break;
                }
            }
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public  void sellTicket() {

    }//执行完，归还钥匙
}
