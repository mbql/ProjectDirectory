package com.zm.shiro.service.impl;

import com.zm.shiro.bean.Permissions;
import com.zm.shiro.bean.Role;
import com.zm.shiro.bean.User;
import com.zm.shiro.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author mbql
 * 用户登录实现类
 * @date 2020/2/23 16:32
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public User getUserByName(String getMapByName) {
        //模拟数据库查询，正常情况此处是从数据库或者缓存查询。
        return getMapByName(getMapByName);
    }

    /**
     * 模拟数据库查询
     *
     * @param userName
     * @return
     */
    private User getMapByName(String userName) {
        //共添加两个用户，两个用户都是admin一个角色，
        //wsl有query和add权限，zhangsan只有一个query权限
        Permissions permissions1 = new Permissions(1, "query");
        Permissions permissions2 = new Permissions(2, "add");
        Set<Permissions> permissionsSet = new HashSet<>();
        permissionsSet.add(permissions1);
        permissionsSet.add(permissions2);
        Role role = new Role(1, "admin", permissionsSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = new User(1, "mbql", "123456", roleSet);
        Map<String, User> map = new HashMap<>();
        map.put(user.getUsername(), user);

        Permissions permissions3 = new Permissions(3, "query");
        Set<Permissions> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions3);
        Role role1 = new Role(2, "user", permissionsSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User(2, "tom", "123456", roleSet1);
        map.put(user1.getUsername(), user1);
        return map.get(userName);
    }

}
