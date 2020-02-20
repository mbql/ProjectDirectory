package com.zm.springmybatis.controller;

import com.zm.springmybatis.entity.Student;
import com.zm.springmybatis.service.StudentService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Student)表控制层
 *
 * @author makejava
 * @since 2020-02-20 14:50:28
 */
@Controller
public class StudentController {
    /**
     * 服务对象
     */
    @Resource
    private StudentService studentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    public Student selectOne(Integer id) {
        return studentService.queryById(id);
    }

    /**
     * 通过分页查询
     *
     * @param index
     * @param size
     * @return
     */
    public List<Student> queryBylimit(Integer index, Integer size) {
        return studentService.queryAllByLimit(index, size);
    }

    /**
     * 添加学生信息
     *
     * @param student
     * @return
     */
    public Student addStudent(Student student) {
        return studentService.insert(student);
    }

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    public Student updateStudent(Student student){
        return studentService.update(student);
    }

    /**
     * 通过Id删除学生信息
      * @param id
     * @return
     */
    public boolean delStudent(Integer id){
        return studentService.deleteById(id);
    }

}
