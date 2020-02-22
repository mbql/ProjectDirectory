package com.zm.security.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zm.security.core.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 用户与角色关系DAO
 * @Author mbql
 * @CreateTime 2019/9/14 15:57
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

}
