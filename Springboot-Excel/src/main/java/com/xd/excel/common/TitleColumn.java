package com.xd.excel.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleColumn implements Serializable {

    // 标题
    private String title;

    // 列宽
    private int column;

}
