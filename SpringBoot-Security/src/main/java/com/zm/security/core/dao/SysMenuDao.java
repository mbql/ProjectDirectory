package com.zm.security.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zm.security.core.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限DAO
 * @Author mbql
 * @CreateTime 2019/9/14 15:57
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

}
