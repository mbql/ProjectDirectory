package com.ht.factory;

/**
 * @author mbql
 * 工厂模式
 *  1、一个调用者想创建一个对象，只要知道其名称就可以了。
 *  2、扩展性高，如果想增加一个产品，只要扩展一个工厂类就可以。
 *  3、屏蔽产品的具体实现，调用者只关心产品的接口。
 * @date 2020/3/26 15:13
 */
public interface Shape {

    //定义一个模型接口
    void draw();

}
