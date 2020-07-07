package com.ht.factory;

/**
 * @author mbql
 * 使用该工厂，通过传递的类型信息来获取实体对象
 * @date 2020/3/26 15:25
 */
public class FactoryPatternDemo {

    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        //获取 Circle 的对象，并调用它的 draw 方法
        factory.getShape("CIRCLE").draw();
        //获取 Rectangle 的对象，并调用它的 draw 方法
        factory.getShape("RECTANGLE").draw();
        //获取 Square 的对象，并调用它的 draw 方法
        factory.getShape("SQUARE").draw();
    }
}
