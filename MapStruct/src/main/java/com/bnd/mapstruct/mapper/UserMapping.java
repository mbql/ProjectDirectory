package com.bnd.mapstruct.mapper;

import com.alibaba.fastjson.JSON;
import com.bnd.mapstruct.entity.User;
import com.bnd.mapstruct.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @Description
 * @Author sueno
 * @Date 2020/11/22 13:11
 */
@Mapper(componentModel = "spring")
public interface UserMapping extends BaseMapping<User, UserVo>{

    @Mapping(target = "gender", source = "sex")
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Override
    UserVo sourceToTarget(User var1);

    @Mapping(target = "sex", source = "gender")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Override
    User targetToSource(UserVo var1);

    default List<UserVo.UserConfig> strConfigToListUserConfig(String config) {
        return JSON.parseArray(config, UserVo.UserConfig.class);
    }

    default String listUserConfigToStrConfig(List<UserVo.UserConfig> list) {
        return JSON.toJSONString(list);
    }

}
