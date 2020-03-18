package com.ht.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ht.entity.Student;
import com.ht.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 学生信息表(Student)表控制层
 *
 * @author makejava
 * @since 2020-03-17 20:18:11
 */
@RestController
@RequestMapping("student")
public class StudentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private StudentService studentService;

    /**
     * 分页查询所有对象
     * @param flag  条件分页
     * @param index 当前页
     * @param size  每页数量
     * @return  返回的对象
     */
    @GetMapping
    public R selectAll(Integer flag, Integer index,Integer size) {
        return success(this.studentService.page(new Page<>(index,size), new QueryWrapper<>(new Student().setFlag(flag))));
    }

    @GetMapping("/page")
    public Object findStudentPage(Integer currentIndex,Integer size , Integer flag){
        return this.studentService.selectStudentPage(new Page<>(currentIndex,size),flag);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.studentService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param student 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(Student student) {
        return success(this.studentService.save(student));
    }

    /**
     * 修改数据
     *
     * @param student 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(Student student) {
        return success(this.studentService.updateById(student));
    }

    /**
     * 删除数据
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping("{id}")
    public R deleteById(@PathVariable Integer id) {
        return success(this.studentService.removeById(id));
    }
}
