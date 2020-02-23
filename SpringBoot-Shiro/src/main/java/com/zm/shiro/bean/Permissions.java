package com.zm.shiro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author mbql
 * 权限实体类
 * @date 2020/2/23 16:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Permissions {

    /**
     * 权限编号
     */
    private  Integer id;
    /**
     * 权限名称
     */
    private String permissionsName;

}
