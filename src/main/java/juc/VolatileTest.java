package juc;

/**
 * volatile关键字 ： 在多线程共享数据时，保证内存中数据可以可见
 * volatile相对于synchronize是种较轻量级的同步机制。
 * volatile不具备互斥性
 * volatile不能保证数据的原子性
 *
 * @author zhouxiang
 * @date 2019/11/10-20:20
 */
public class VolatileTest {

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        new Thread(demo).start();
        while (true){
            if (demo.isFlag()){
                System.out.println("*******************");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {
    private boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag:" + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }
}
