package com.xd.excel.service;
import com.xd.excel.entity.ExcelModel;
import com.xd.excel.enums.SexType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author sueno
 * @Date 2021/4/18 11:20
 */
@Service
public class ExcelExportService implements CommandLineRunner {

    public static List<ExcelModel> list = null;

    @Override
    public void run(String... args) throws Exception {
        list = new ArrayList<>();
        ExcelModel model = new ExcelModel();
        model.setName("sueno");
        model.setAge(22);
        model.setCreateTime(LocalDateTime.now());
        model.setSex(SexType.MALE);
        ExcelModel model1 = new ExcelModel();
        model1.setName("slp");
        model1.setAge(23);
        model1.setCreateTime(LocalDateTime.now());
        model1.setSex(SexType.FEMALE);
        list.add(model);
        list.add(model1);
    }

    /**
     * 模拟数据库操作
     * @return 返回结果数据
     */
    public List<ExcelModel> findAll() {
        return list;
    }

}
