package com.ht.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ht.dto.StudentDTO;
import com.ht.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 学生信息表(Student)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-17 20:18:10
 */
public interface StudentDao extends BaseMapper<Student> {

    /**
     * <p>
     * 查询 : 根据flag状态查询学生列表，分页显示
     * </p>
     *
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @param flag 状态
     * @return 分页对象
     */
    @Select("SELECT id,student_name,age,sex,addr,grade_id,flag FROM student WHERE flag=#{flag}")
    IPage<Student> selectPageVo(Page<?> page, Integer flag);

    /**
     * 查询所有学生信息
     * @return  返回学生对象
     */
    @Select("SELECT id,student_name,age,sex,addr,grade_id,flag FROM student")
    List<StudentDTO> findAll();

}
