import org.junit.Test;
import org.omg.IOP.ComponentIdHelper;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author zhouxiang
 * @date 2019/9/8-22:02
 * lambda的表达语法，根据操作符将表达分为两部分
 * 左边：Lambda的参数列表
 * 右边：表达式需要执行的功能。即Lambda体
 */
public class Demo1 {
    @Test
    public void test1() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello lambda");
            }
        };
        new Thread(r).start();
    }

    @Test
    public void test2() {
        /**
         *@描述 Lambda表达式中没有参数和返回值 ,lambda体
         */
        new Thread(() -> System.out.println("hello lambda")).start();
    }

    /**
     * @描述 多个参数，有返回值，有Lambda体
     */
    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println(x);
            System.out.println(y);
            return Integer.compare(x, y);
        };
        int i = comparator.compare(1, 2);
        System.out.println(i);
    }

    /**
     * @描述 如果只有一个参数，或者lambda体只有一条语句，() 或者{}可以不写
     */

    @Test
    public void test4() {
        Consumer<String> consumer = (x) -> System.out.println(x);
//        Consumer <String> consumer = x -> {System.out.println(x)};
        consumer.accept("lambda表达格式4");
    }

    /*
     *类型推断
     * @param
     * @return void
     * @author zhouxiang
     * @date 2019/9/20 23:40
     */
    public void test5() {
        test6(new HashMap<>());// 根据方法参数推断类型

    }

    public void test6 ( Map<String,String> map) {
    }
}
