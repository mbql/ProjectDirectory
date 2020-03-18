package com.ht.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ht.dto.StudentDTO;
import com.ht.entity.Student;

import java.util.List;

/**
 * 学生信息表(Student)表服务接口
 *
 * @author makejava
 * @since 2020-03-17 20:18:10
 */
public interface StudentService extends IService<Student> {

    /**
     * 根据学生表进行分页
     * @param page  页面
     * @param flag 状态
     * @return  返回对象
     */
    IPage<Student> selectStudentPage(Page<StudentDTO> page, Integer flag);

    /**
     * 查询所有学生
     * @return  返回对象集合
     */
    List<StudentDTO> findAll();

}
