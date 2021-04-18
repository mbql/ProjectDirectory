package com.xd.excel.controller;

import com.xd.excel.entity.ExcelModel;
import com.xd.excel.service.ExcelExportService;
import com.xd.excel.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description
 * @Author sueno
 * @Date 2021/4/18 11:18
 */
@RestController
@RequestMapping(value = "/excel")
public class ExcelExportController {

    @Autowired
    private ExcelExportService excelExportService;

    @PostMapping(value = "/export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        List<ExcelModel> list = excelExportService.findAll();
        ExcelUtil.exportExcel(request, response, "用户数据", list, ExcelModel.class);
    }

}
