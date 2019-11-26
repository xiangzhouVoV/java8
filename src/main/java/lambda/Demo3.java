package lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.Date;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用:lambda体中的内容有方法已经实现了，可以使用方法引用
 * (实际是lambda另外一种表现形式)
 *
 * @author zhouxiang
 * @date 2019/9/24-23:43
 */
public class Demo3 {
    /**************************第一种:对象::实例方法名*****************************/
    @Test
    public void test1() {
        Date date = new Date();
        Supplier<Long> sup = () -> date.getTime();
        Supplier<Long> sup1 = date::getTime;
        System.out.println(sup.get());
        System.out.println(sup1.get());
    }

    @Test
    public void test() {
        Consumer<String> consumer = x -> System.out.println(x);
        // lambda体中内容已经有方法实现了，方法引用
        Consumer<String> com = System.out::println;
        consumer.accept("hello");
        com.accept("world");

    }

    /**************************第二种:类名::静态方法名*****************************/
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        System.out.println(com.compare(1, 3));
        Comparator<Integer> compare = Integer::compare;
        System.out.println(compare.compare(1, 3));
    }

    /**************************第三种:类名::实例方法名*****************************/
    BiPredicate<String, String> bp = (str1, str2) -> str1.equals(str2);
    // 当lambda表达式中的一个参数是方法的调用者，另外一个参数是调用的方法传入参数
    BiPredicate <String,String> bp1 = String::equals;
}
