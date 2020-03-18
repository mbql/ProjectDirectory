package com.ht.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ht.dao.StudentDao;
import com.ht.dto.StudentDTO;
import com.ht.entity.Student;
import com.ht.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生信息表(Student)表服务实现类
 *
 * @author makejava
 * @since 2020-03-17 20:18:10
 */
@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @Resource
    private StudentDao studentDao;

    public IPage<Student> selectStudentPage(Page<StudentDTO> page, Integer flag) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        return studentDao.selectPageVo(page, flag);
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentDao.findAll();
    }
}
