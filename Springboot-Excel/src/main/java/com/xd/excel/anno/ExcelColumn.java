package com.xd.excel.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

	// 定义字段在excel的单元格列坐标位置
	int order() default 9999;

	// 定义列坐标对应的标题
	String title() default "";

	// 定义列宽
	int column() default 100;

	// 定义日期显示格式
	String pattern() default "yyyy-MM-dd HH:mm:ss";

}
