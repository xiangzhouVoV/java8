package juc.produtorAndconsumer;

/**
 * 当商品为1的时候，表示库存已满。
 * 会出现生产者线程一直等待。
 * <p>
 * 因为生产者设置了延迟时间，所以消费者最后一次消费没有商品时，调用wait方法
 * 但是生产者还有3次机会。当消费者最后一次消费结束，没有线程去唤醒生产者。
 * <p>
 * 解决方法：去除else
 *
 * @author zhouxiang
 * @date 2019/11/17-11:02
 */
public class ProductorAndConsumerTest1 {
    public static void main(String[] args) {
        Clerk1 clerk1 = new Clerk1();
        Produtor1 produtor = new Produtor1(clerk1);
        Consumer1 consumer = new Consumer1(clerk1);
        new Thread(produtor, "生产者A").start();
        new Thread(consumer, "消费者B").start();

    }
}

class Clerk1 {
    private int product = 0;

    /**
     * 进货
     *
     * @return void
     * @author zhouxiang
     * @date 2019/11/16 23:36
     */
    public synchronized void add() {
        if (product >= 1) {
            System.out.println("库存已满");
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + ++product);
        this.notifyAll();

    }

    /**
     * 卖货
     *
     * @return void
     * @author zhouxiang
     * @date 2019/11/16 23:37
     */
    public synchronized void sale() {
        if (product <= 0) {
            System.out.println("缺货了！");
            try {
                this.wait();// wait方法等待这个位置，下次拥有执行权会继续上次没有完成的操作
            } catch (InterruptedException ignored) {
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + --product);
        this.notifyAll();


    }

}

/**
 * 生产者
 */
class Produtor1 implements Runnable {
    private Clerk1 clerk1;

    public Produtor1(Clerk1 clerk1) {
        this.clerk1 = clerk1;
    }

    @Override
    public void run() {

        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);// sleep 不会释放锁资源，但是会释放cpu资源
            } catch (InterruptedException ignored) {
            }
            clerk1.add();
        }
    }
}

/**
 * 消费者
 */
class Consumer1 implements Runnable {
    private Clerk1 clerk1;

    public Consumer1(Clerk1 clerk1) {
        this.clerk1 = clerk1;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk1.sale();
        }
    }
}