package juc;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性
 *
 * @author zhouxiang
 * @date 2019/11/16-16:12
 */
public class AtomicTest {
     public static void main(String[] args) {
         AtomicDemo demo = new AtomicDemo();
         for (int i = 0; i < 10; i++) {
             new Thread(demo).start();
         }
     }
}

class AtomicDemo implements Runnable {
    AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(incredit());
    }

    private int incredit(){
        return  atomicInteger.getAndIncrement();
    }
}