import com.sun.org.apache.regexp.internal.REUtil;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 内置核心函数式接口
 *
 * @author zhouxiang
 * @date 2019/9/22-19:57
 * <p>
 * Consumer : 消费型接口
 * void accept(T t);
 * <p>
 * Supplier : 供给型接口
 * T get()
 * <p>
 * Function : 函数式接口
 * R apply(T t)
 * <p>
 * Predicate <T> : 判断型接口
 * boolean test(T t)s
 */
public class Demo2 {
    /*******************************判断型********************************************/
    @Test
    public void test3 () {
        List<String> str = Arrays.asList("welcome", "to", "hangzhou");
        List<String> strings = filterStr(str, (list) -> list.length() > 2);
        System.out.println(strings);
    }
    public List<String> filterStr (List<String> strList, Predicate<String> predicate){
        ArrayList<String> list = new ArrayList<>();
        for (String s : strList) {
            if (predicate.test(s))
            list.add(s);
        }
        return list;
    }
    /*******************************函数型********************************************/
    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(i);
        }
        // lambda传入的参数不是list，str才是传入的实参
        String s1 = convertStr(list, (str) -> {
            StringBuilder builder = new StringBuilder();
            for (Integer s : str) {
                builder.append(s);
                builder.append(",");
            }
            return builder.toString();
        });
        System.out.println(s1);
    }

    public String convertStr (List<Integer> str, Function<List<Integer>,String> function){
        return  function.apply(str);
    }


    /*******************************供给型********************************************/
    @Test
    public void test1() {
        ArrayList<Object> list = new ArrayList<>();
        list.add("Tom");
        list.add("Jack");
        String str = "yingmu";
        Object object = getObject( () -> str);
        System.out.println(object);
    }

    public Object getObject( Supplier<Object> supplier) {
        return supplier.get();
    }


    /*****************************消费型*****************************************/

    @Test
    public void test() {
        happy(100, (consumer) -> {
            int i = 10 + consumer;
            System.out.println(i);
        });
    }

    public void happy(Integer money, Consumer<Integer> consumer) {
        consumer.accept(money);
    }
}
