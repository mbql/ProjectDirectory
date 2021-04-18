package com.xd.excel.entity;

import com.alibaba.fastjson.JSONObject;
import com.xd.excel.anno.ExcelColumn;
import com.xd.excel.enums.SexType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ExcelModel implements Serializable {

    @ExcelColumn(order=0, title = "姓名", column = 10)
    private String name;

    @ExcelColumn(order=1, title = "年龄", column = 10)
    private Integer age;

    @ExcelColumn(order=2, title = "创建时间", column = 24)
    private LocalDateTime createTime;

    @ExcelColumn(order=3, title = "性别", column = 10)
    private SexType sex;

    @Override
    public String toString() { return JSONObject.toJSONString(this); }
    
}
