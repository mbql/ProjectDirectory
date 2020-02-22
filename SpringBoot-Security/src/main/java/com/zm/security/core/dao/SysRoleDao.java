package com.zm.security.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zm.security.core.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 角色DAO
 * @Author mbql
 * @CreateTime 2019/9/14 15:57
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

}
