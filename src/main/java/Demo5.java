import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream的操作
 * <p>
 * 1.创建Stream
 * <p>
 * 2.中间操作
 * <p>
 * 3.终止操作
 *
 * @author zhouxiang
 * @date 2019/10/14-21:55
 */
public class Demo5 {
    List<Student> studentList = Arrays.asList(
            new Student("张三", 22, "33333333"),
            new Student("李四", 19, "33232323"),
            new Student("王五", 22, "56789012"),
            new Student("陈六", 25, "12345679"),
            new Student("狗蛋", 24, "30987654"),
            new Student("铁锤", 26, "35784903"),
            new Student("jack", 20, "67823462")
    );

    @Test
    public void test() {

        // 中间操作，中间操作不会有任何操作
        Stream<Student> newStream = studentList.stream().filter(student -> {
            // filter 中流中排出一些不符合元素
            System.out.println("20岁以上");
            return student.getAge() > 20;
        });
        // 终止操作 在终止操作一次执行
        newStream.forEach(System.out::println);
    }

    @Test
    public void test1() {
        Stream<Student> stream = studentList.stream().filter((s) -> {
            System.out.println("执行filter");
            return s.getAge() < 22;
        }).limit(2);
        // limit:截断流,使元素不超过指定数量，limit达到指定数量，直接中断操作。
        stream.forEach(System.out::println);
    }

    @Test
    public void test2() {
        studentList.stream().filter(student -> student.getAge() > 20)
                .skip(2)
                .forEach(System.out::println);
        // skip是跳过钱n个元素，如果不够就返回空流，与limit互补
    }

    @Test
    public void test3() {
        studentList.stream().filter(student -> student.getAge() > 20)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
        // distinct，通过流所生产的hashcode和equal方法去重
    }

    @Test
    public void test4() {
        /*
            映射
            map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
            flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
	    */
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        Stream<String> stream = list.stream().map((str) -> str.toUpperCase());// map会返回一个新Stream
        stream.forEach(System.out::println);

        Stream<Stream<Character>> stream1 = list.stream().map(Demo5::filterCharacter);
        stream1.forEach(sm->{
            sm.forEach(System.out::println);
        });

        Stream<Character> stream3 = list.stream()
                .flatMap(Demo5::filterCharacter);
        stream3.forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    @Test
    public void test5 () {
        Stream<String> stream = studentList.stream().map(Student::getName);
        stream.sorted().forEach(System.out::println);

        Stream<Student> stream1 = studentList.stream().sorted((x, y) -> {
            if (x.getAge() == y.getAge()) {
                return x.getName().compareTo(y.getName());// 计算出字符串hash值，排序
            } else {
                return Integer.compare(x.getAge(), y.getAge());
            }
        });
        stream1.forEach(System.out::println);
    }
    @Test
    public void test6 () {
       String  s1 = "张三";
       String  s2 = "王五";
        int hashCode = s1.hashCode();
        int ha = s2.hashCode();
        System.out.println(hashCode);// 774889
        System.out.println(ha);// 937065
    }

}
