package juc.produtorAndconsumer;

/**
 * 生产者，消费者，模拟店员卖货
 *
 * @author zhouxiang
 * @date 2019/11/16-23:29
 */
public class ProductorAndConsumerTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Produtor produtor = new Produtor(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(produtor,"生产者A").start();
        new Thread(consumer,"消费者B").start();
    }
}

class Clerk {
    private int product = 0;
    /**
     * 进货
     *
     * @return void
     * @author zhouxiang
     * @date 2019/11/16 23:36
     */
    public synchronized void add() {
        if (product >= 10) {
            System.out.println("库存已满");
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        } else {
            System.out.println(Thread.currentThread().getName() + ":" + ++product);
            this.notifyAll();
        }
    }
    /**
     * 卖货
     *
     * @return void
     * @author zhouxiang
     * @date 2019/11/16 23:37
     */
    public synchronized void sale() {
        if (product<=0){
            System.out.println("缺货了！");
            try {
                this.wait();// wait方法等待这个位置，下次拥有执行权会继续上次没有完成的操作
            } catch (InterruptedException ignored) {
            }
        } else {
            System.out.println(Thread.currentThread().getName() + ":" + --product);
            this.notifyAll();
        }
    }
}

/**
 * 生产者
 */
class Produtor implements Runnable{
    private Clerk clerk;

    public Produtor(Clerk clerk) {
        this.clerk = clerk;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.add();
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable{
    private Clerk clerk;

    public Consumer(Clerk clerk1) {
        this.clerk = clerk1;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}
