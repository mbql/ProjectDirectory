package com.ht.dto;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 学生信息表(Student)表实体类
 *
 * @author makejava
 * @since 2020-03-17 20:18:11
 */
@Data
@ToString
@SuppressWarnings("serial")
public class StudentDTO implements Serializable {

    /**主键*/
    private Integer id;
    /**学生姓名*/
    private String studentName;
    /**年龄*/
    private Integer age;
    /**性别*/
    private String sex;
    /**家庭住址*/
    private String addr;

}
