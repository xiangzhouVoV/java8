package stream;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * StreamApI 二 终止操作
 *
 * @author zhouxiang
 * @date 2019/10/16-10:10
 */
public class Demo6 {
    List<Student> studentList = Arrays.asList(
            new Student("张三", 22, "33333333"),
            new Student("李四", 19, "33232323"),
            new Student("王五", 22, "56789012"),
            new Student("陈六", 25, "12345679"),
            new Student("狗蛋", 24, "30987654"),
            new Student("铁锤", 26, "35784903"),
            new Student("jack", 20, "67823462")
    );

    /*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
    @Test
    public void test() {
        boolean b = studentList.stream().allMatch(stu -> stu.getAge() < 25);
        boolean b1 = studentList.stream().anyMatch(student -> student.getAge() < 25);
        boolean b2 = studentList.stream().noneMatch(student -> student.getAge() > 28);
        Optional<Student> first = studentList.stream().findFirst();
        // 返回一个Optional，为了避免空指针异常
        Student stu = first.get();
        // 如果为空的话，orElse可以放入一个其他的对象
        first.orElse(new Student("狗子", 22, "232323"));
        Student anyStudent = studentList.stream().findAny().get();
        System.out.println(anyStudent.getName());
    }

    // 流一旦执行了终止操作后，不能继续使用
    @Test
    public void test1() {
        long count = studentList.stream().count();
        System.out.println(count);
        Student student = studentList.stream().max(Comparator.comparingInt(Student::getAge)).get();
        System.out.println(student.getName());
        //提取出流中对象的age属性
        Optional<Integer> minAge = studentList.stream().map(Student::getAge)
                // 在挑选出最小值
                .min(Integer::compareTo);
        System.out.println(minAge.get());

    }


    @Test
    public void test2() {
        /*
		归约
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	    */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream()
                .reduce(0, Integer::sum);
        // identity相当于一初始值，并且返回一个与identity类型一致的对象
        System.out.println(sum);//55

        // 返回一个Optional
        Optional<Integer> reduce = studentList.stream().map(Student::getAge).reduce(Integer::sum);
        Integer ageSum = reduce.get();
        System.out.println(ageSum);
    }

    @Test
    public void test3() {
        //collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
        List<String> list1 = studentList.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(list1);

        Set<Integer> set = studentList.stream().map(Student::getAge).collect(Collectors.toSet());
        System.out.println(set);

        HashSet<String> set1 = studentList.stream().map(Student::getSno).collect(Collectors.toCollection(HashSet::new));
        System.out.println(set1);
    }

    @Test
    public void test4() {
        BigDecimal o = new BigDecimal("0");
        BigDecimal b = new BigDecimal("1.01");
        BigDecimal b1 = new BigDecimal("20.2");
        BigDecimal b2 = new BigDecimal("300.3");
        o.add(b);
        System.out.println(o);
        o = o.add(b);
        System.out.println(o);



    }


}
