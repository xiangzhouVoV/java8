package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程方式
 * @author zhouxiang
 * @date 2019/11/16-22:30
 */
public class CallbaleTest {
    public static void main(String[] args) {
        CallbaleDemo demo = new CallbaleDemo();
        // 执行Callable创建线程方式，需要FutureTask实现类支持用于接受返回结果
        FutureTask<Integer> futureTask = new FutureTask<>(demo);
        new Thread(futureTask).start();
        try {
            // futureTask.get();获取实现Callable线程返回结果。
            Integer sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
class CallbaleDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 50; i++) {
            sum += i;
        }
        return sum;
    }
}