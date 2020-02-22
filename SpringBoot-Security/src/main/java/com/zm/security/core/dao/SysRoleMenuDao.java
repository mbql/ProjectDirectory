package com.zm.security.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zm.security.core.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限关系DAO
 * @Author mbql
 * @CreateTime 2019/9/14 15:57
 */
@Mapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuEntity> {

}
