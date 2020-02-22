package com.zm.security;
import com.zm.security.core.entity.SysUserEntity;
import com.zm.security.core.entity.SysUserRoleEntity;
import com.zm.security.core.service.SysUserRoleService;
import com.zm.security.core.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringbootSecurityApplicationTests {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Test
      void contextLoads() {
        //注册用户信息
        SysUserEntity sysUserEntity = new SysUserEntity();
        //设置用户属性
        sysUserEntity.setUsername("mbql");
        sysUserEntity.setPassword(bCryptPasswordEncoder.encode("123456"));
        sysUserEntity.setStatus("NORMAL");
        sysUserService.save(sysUserEntity);
        System.out.println("添加用户成功！！！");
        //给用户分配角色 1.ADMIN 2.USER
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setUserId(sysUserEntity.getUserId());
        sysUserRoleEntity.setRoleId(2L);
        sysUserRoleService.save(sysUserRoleEntity);
        System.out.println("给用户分配角色成功！！！");
    }
}
