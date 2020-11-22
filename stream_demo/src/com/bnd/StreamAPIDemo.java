package com.bnd;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author mbql
 * @date 2020/8/15 13:00
 */
public class StreamAPIDemo {

    public static void main(String[] args) {

        // 初始化一个Map集合
        Map<String, Integer> map = new HashMap<String, Integer>(){{
            put("apple", 15);
            put("banana", 36);
            put("watermelon", 18);
            put("strawberry", 48);
            put("grape", 56);
            put("watermelon", 28);
        }};
        // 通过Stream流获取输出所需要的数据 并且根据值大小倒序排序
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .filter(key -> key.getKey().contains("grape") || key.getValue() > 26).forEach(System.out::println);

        // 使用mapToInt函数实现值求和 并排除指定的元素
        int sum = map.entrySet().stream().filter(e -> !e.getKey().contains("grape")).mapToInt(Map.Entry::getValue).sum();
        System.out.println("求和为：" + sum);

        /**
         * 除了上面这种求和还有下面这种 （包括：求和、平均值、最小值、最大值等）--> 其实还有一种 return reduce(1, Integer::sum) 方式
         * 但这种（一般只针对List集合有效）
         * 这里说一下：map集合会对相同的键做hash处理，若是key相同则重复的不再累加 不再累赘
         */
        IntSummaryStatistics statistics = map.entrySet().stream().mapToInt(value -> value.getValue()).summaryStatistics();
        long sum1 = statistics.getSum();    // 求和
        int max = statistics.getMax();      // 最大值
        int min = statistics.getMin();      // 最小值
        double average = statistics.getAverage();   // 平均值
        long count = statistics.getCount();         // 统计集合元素数量
        System.out.println("求和：" + sum1 + "-->最大值：" + max + "-->最小值：" + min + "-->平均值：" + average + "-->元素数量：" + count);

        /**
         * 使用peek函数中间遍历输出 方便对集合元素debug调试
         * 排除banana元素的输出
         */
        System.out.println("--------------------------------------");
        map.entrySet().stream().filter(key -> !key.getKey().contains("banana")).peek(value -> System.out.println(value)).collect(Collectors.toList());

        /**
         * skip、limit函数的使用
         * skip 默认从首元素开始跳过 方便取全几个元素
         * limit 限制输出的行数 类似以分页
         * 根据value降序排序 并 跳过value最大的元素
         */
        System.out.println("--------------------------------------");
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder())).skip(1).
                limit(6).forEach(System.out::println);

        /**
         * xxxMatch函数根据条件匹配所需要的数据
         * findFirst、findAny 默认这2种匹配的数据都是一样的 但在并行流中是不同的
         */
        System.out.println("--------------------------------------");
        // 精确匹配所有才能返回true
        boolean allMatch = map.entrySet().stream().allMatch(e -> e.getKey().contains("watermelon"));
        // 包含其中任意一个元素 即返回true
        boolean anyMatch = map.entrySet().stream().anyMatch(e -> e.getKey().contains("watermelon"));
        // 集合中不包含该元素 返回true
        boolean noneMatch = map.entrySet().stream().noneMatch(e -> e.getKey().contains("tom"));
        System.out.println(allMatch + "-->" + anyMatch + "-->" +noneMatch);

        // findFirst()  apple=15
        Optional<Map.Entry<String, Integer>> optional = map.entrySet().stream().
                sorted(Comparator.comparing(e -> e.getValue())).findFirst();        // 默认升序排序 取第一个
        if (optional.isPresent());System.out.println(optional.get());     // 若是存在则输出
        // findAny()    apple=15
        Optional<Map.Entry<String, Integer>> optional1 = map.entrySet().stream().
                sorted(Comparator.comparing(e -> e.getValue())).findAny();        // 默认升序排序 取第一个
        if (optional.isPresent());System.out.println(optional1.get());     // 若是存在则输出

        /**
         * findFirst() 和 findAny() 区别
         * 分别在并行流中执行    （还是使用上面的函数方法）
         * 说明一下：在单向流中这两个方法没什么区别 ， 区别在与并行流中findAny()是随机的
         * 而findFirst始终取第一个元素
         */
        // findFirst()      apple=15
        System.out.println("--------------------------------------");
        Optional<Map.Entry<String, Integer>> parallelOptional = map.entrySet().parallelStream().
                sorted(Comparator.comparing(e -> e.getValue())).findFirst();        // 默认升序排序 取第一个 并行流中执行
        if (optional.isPresent());System.out.println(parallelOptional.get());     // 若是存在则输出
        // findAny()    banana=36
        Optional<Map.Entry<String, Integer>> parallelOptional1 = map.entrySet().parallelStream().
                sorted(Comparator.comparing(e -> e.getValue())).findAny();        // 默认升序排序 并行流中执行
        if (optional.isPresent());System.out.println(parallelOptional1.get());     // 若是存在则输出

        /**
         * flatMap()函数方法解决维度升降
         * 列如[1,2],[1,3,5] 这两个数组，返回（1,1）,(1,3),(1,5)数对？
         */
        System.out.println("--------------------------------------");
        List<Integer> integers = Arrays.asList(1, 2);
        List<Integer> integers1 = Arrays.asList(1, 3, 5);
        integers.stream().flatMap(a -> integers1.stream().map(b -> new int[] {a, b})).
                collect(Collectors.toList()).forEach(e -> System.out.println(Arrays.toString(e)));

        /**
         * 完整的Stream流输出 是不是很简单
         * 方便 + 简洁 + 效率高
         */
        System.out.println("--------------------------------------");
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder())).skip(1)
                .filter(key -> !key.getKey().contains("strawberry")).map(value -> value.getKey() +":"+ value.getValue()).distinct()
                .limit(2).collect(Collectors.toList()).forEach(System.out::println);

        /**
         * 讲一下排序中Comparator.reverseOrder()与reversed()区别
         */
        System.out.println("--------------------------------------");
        map.entrySet().stream().filter(key -> !key.getKey().contains("strawberry"))
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("--------------------------------------");
        map.entrySet().stream().filter(key -> !key.getKey().contains("strawberry"))
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder())).collect(Collectors.toList())
                .forEach(System.out::println);
        /**
         * 这2种的区别如下：
         * 1、reversed()会将前面的所有条件起作用 （即全部降序排序）
         * 2、Comparator.reverseOrder()只会对单一条件进行降序排序
         * 通过讲解这2种根据自己业务场景进行选择，但一般会优先使用Comparator.reverseOrder()
         * 降序排序（能够根据单个字段排序，不会影响到其他前面的条件）
          */

    }

}
