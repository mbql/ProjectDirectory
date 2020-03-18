package com.ht;

import com.ht.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {

    @Resource
    private StudentService studentService;

    @Test
    void contextLoads() {
        // System.out.println(this.studentService.page(new Page<>(0,2), new QueryWrapper<>(new Student().setFlag(0))));
        /*IPage<Student> iPage = this.studentService.selectStudentPage(new Page<>(1, 5), 0);
        System.out.println("总页数："+iPage.getPages());
        System.out.println("总条数:"+iPage.getTotal());
        System.out.println("当前页码:"+iPage.getCurrent());
        System.out.println("每页数量:"+iPage.getSize());*/
        // studentService.findAll().forEach(System.out::println);
        //使用lambda表达式圆括号可以无参及有参，一个参数()可以省咧
        // studentService.findAll().forEach(list -> System.out.println(list));

        //匿名内部类的形式开启一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程一-----------------------------");
            }
        }).start();
        // lambda表达式使用
        new Thread(() -> System.out.println("线程二---------------------")).start();

        //常见的函数式接口：Runnable、 Comparable--排序(是一个函数式接口吗？)
        Comparable<Integer> comparable = new Comparable<Integer>() {
            @Override
            public int compareTo(Integer o) {
                return 0;
            }
        };
        //Lamada表达式的用法
        Comparable<Integer> com = (a) -> a;
        int i = com.compareTo(3);
        System.out.println(i);

        //jdk1.8中的函数式接口
        new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        };
        //对上面方法的引用
        Supplier<Double> supplier = () -> Math.random();
        Supplier<Double> supplier1 = Math::random;
        System.out.println("-------------------------------------");

        new Consumer<String>() {
            @Override
            public void accept(String s) {
                PrintStream out = System.out;
                out.println();
            }
        };
        PrintStream out = System.out;
        Consumer<String> consumer = (s) -> out.println(s);
        Consumer<String> consumer1 = out::println;
        consumer1.accept("mbql");
        System.out.println("------------------------------------------");

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        Comparator<String> comparator1 = (o1,o2) -> o1.compareTo(o2);
        Comparator<String> comparator2 = String::compareTo;
        System.out.println("-------------------------------------------");
    }
}
