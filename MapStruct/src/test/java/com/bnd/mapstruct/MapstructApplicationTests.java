package com.bnd.mapstruct;

import com.bnd.mapstruct.entity.User;
import com.bnd.mapstruct.mapper.UserMapping;
import com.bnd.mapstruct.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

@Slf4j
@SpringBootTest
class MapstructApplicationTests {

    @Resource
    private UserMapping userMapping;

    @Test
    public void testDemoDTO() {
        User user = new User();
        user.setId(1L);
        user.setUsername("mbql");
        user.setPassword("123");
        user.setSex(1);
        user.setBirthday(LocalDate.of(1999, 07,13));
        user.setCreateTime(LocalDateTime.now());
        user.setConfig("[{\n" +
                "                \"field1\": \"Test DTO Config\",\n" +
                "                \"field2\": 0\n" +
                "}]");

        UserVo userVo = userMapping.sourceToTarget(user);

        log.info("User：{}", user);

        log.info("UserVo：{}", userVo);

    }

    @Test
    public void testDemoDTO2() {
        UserVo.UserConfig userConfig = new UserVo.UserConfig();
        userConfig.setField1("Test DTO Config Field");
        userConfig.setField2(500);

        UserVo userVo = new UserVo()
                .setId(1L)
                .setUsername("mbql")
                .setGender(2)
                .setCreateTime("2020-11-22 15:32:54")
                .setBirthday(LocalDate.of(1999, 07, 13))
                .setConfig(Collections.singletonList(userConfig))
                .setPassword("qwe123");
        User user = userMapping.targetToSource(userVo);

        log.info("UserVo: {}", userVo);

        log.info("User: {}", user);
    }


}
