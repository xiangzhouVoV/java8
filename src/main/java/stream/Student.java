package stream;

import java.sql.SQLOutput;

/**
 * 学生测试类
 * @author zhouxiang
 * @date 2019/10/14-22:20
 */
public class Student {
    private String name ;
    private Integer age ;
    private String sno;

    public Student(String name, Integer age, String sno) {
        this.name = name;
        this.age = age;
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    @Override
    public String toString() {
        return name+"="+age;
    }


}
