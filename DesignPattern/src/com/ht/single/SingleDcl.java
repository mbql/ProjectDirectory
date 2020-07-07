package com.ht.single;

/**
 * @author mbql
 * 双重校验锁模式，也称为双检锁
 * @date 2020/3/26 14:43
 */
public class SingleDcl {

    private static SingleDcl instance;

    private SingleDcl (){};

    public static SingleDcl getInstance(){
        if (instance == null){
            synchronized (SingleDcl.class){
                if (instance == null) {
                    instance = new SingleDcl();
                }
            }
        }
        return instance;
    }
}
