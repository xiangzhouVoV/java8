package stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * stream流
 * @author zhouxiang
 * @date 2019/10/11-23:27
 */
public class Demo4 {
    @Test
    public void test(){
        // 创建Stream流几种方式
        ArrayList<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // Arrays中静态方法获取
        String[] strArray = new String[10];
        Stream<String> stream1 = Arrays.stream(strArray);

        // 通过Stream的静态方法of（）
        Stream<ArrayList<String>> stream2 = Stream.of(list);
        Stream<String> stream3 = Stream.of("aaa", "bb", "cc");

        // 创建无限流
        // 迭代
        Stream<Integer> stream4 = Stream.iterate(0, x -> x + 2);
        stream4.limit(10).forEach(System.out::println);

        // 生成
        Stream<Double> stream5 = Stream.generate(() -> Math.random());
        stream5.limit(10).forEach(System.out::println);
    }
}
