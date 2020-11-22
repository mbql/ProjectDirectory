package com.sueno.test;

/**
 * @author mbql
 * @date 2020/7/8 22:06
 */

import com.starter.config.HttpAutoConfiguration;
import com.starter.config.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HttpAutoConfiguration.class})
public class SpringBootTests {

    @Test
    public void test(){
        String html = new HttpClient().getHtml();
        System.out.println(html);
    }

}
