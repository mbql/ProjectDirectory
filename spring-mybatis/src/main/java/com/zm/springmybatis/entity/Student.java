package com.zm.springmybatis.entity;

import java.io.Serializable;

/**
 * (Student)实体类
 *
 * @author makejava
 * @since 2020-02-20 14:50:25
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -48855354234305783L;
    /**
    * 学生编号
    */
    private Integer id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 性别
    */
    private String sex;
    /**
    * 地址
    */
    private String addr;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

}
