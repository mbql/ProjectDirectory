package com.zm.shiro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

/**
 * @author mbql
 * 角色实体类
 * @date 2020/2/23 16:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

    /**
     * 角色编号
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 权限集合
     */
    private Set<Permissions> permissions;

}
