package juc.produtorAndconsumer;

/**
 *
 *
 * @author zhouxiang
 * @date 2019/11/17-11:02
 */
public class ProductorAndConsumerTest2 {
    public static void main(String[] args) {
        Clerk2 Clerk2 = new Clerk2();
        Produtor2 produtor = new Produtor2(Clerk2);
        Consumer2 consumer = new Consumer2(Clerk2);
        new Thread(produtor, "生产者A").start();
        new Thread(consumer, "消费者B").start();
        new Thread(produtor, "生产者C").start();
        new Thread(consumer, "消费者D").start();

    }
}

class Clerk2 {
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
class Produtor2 implements Runnable {
    private Clerk2 Clerk2;

    public Produtor2(Clerk2 Clerk2) {
        this.Clerk2 = Clerk2;
    }

    @Override
    public void run() {

        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);// sleep 不会释放锁资源，但是会释放cpu资源
            } catch (InterruptedException ignored) {
            }
            Clerk2.add();
        }
    }
}

/**
 * 消费者
 */
class Consumer2 implements Runnable {
    private Clerk2 Clerk2;

    public Consumer2(Clerk2 Clerk2) {
        this.Clerk2 = Clerk2;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            Clerk2.sale();
        }
    }
}