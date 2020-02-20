package com.zm.test;

import com.zm.springmybatis.controller.StudentController;
import com.zm.springmybatis.entity.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author mbql
 * @date 2020/2/20 15:34
 */
public class TestSpringMybatis {
    private final  ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private final  StudentController bean = context.getBean(StudentController.class);
    @Test
    public void queryId(){
        Integer id = 2 ;
        Student student = bean.selectOne(id);
        System.out.println(student);
    }

    @Test
    public void queryBylimit(){
        Integer index = 3;
        Integer size = 3;
        List<Student> students = bean.queryBylimit(index, size);
        for (Student student:
             students) {
            System.out.println(student);
        }
    }
    @Test
    public void addStudent(){
        Student student = new Student();
        student.setName("mbql");
        student.setSex("男");
        student.setAddr("深圳");
        Student student1 = bean.addStudent(student);
        System.out.println("添加该学生成功："+student1);
    }
    @Test
    public void updateStudent(){
        Student student = new Student();
        student.setId(8);
        student.setName("tom");
        student.setSex("女");
        student.setAddr("广州");
        Student student1 = bean.updateStudent(student);
        System.out.println("更新该学生编号为"+student1.getId()+"成功！！！");
    }
    @Test
    public void delStudent(){
        Integer id = 8;
        boolean flag = bean.delStudent(id);
        System.out.println("删除该学生成功："+flag);
    }

















}
