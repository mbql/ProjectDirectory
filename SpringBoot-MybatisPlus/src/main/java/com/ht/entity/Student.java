package com.ht.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 学生信息表(Student)表实体类
 *
 * @author makejava
 * @since 2020-03-17 20:18:10
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@TableName(value = "student")
public class Student extends Model<Student> {
    //主键
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    //学生姓名
    private String studentName;
    //年龄
    private Integer age;
    //性别
    private String sex;
    //家庭住址
    private String addr;

    private Integer gradeId;
    @TableLogic
    private Integer flag;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
