package com.ht.simplesecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author mbql
 * @date 2020/3/30 14:51
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 此次简单的演示，对密码不加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        // return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 配置用户名/密码有三种方式：
     * 1、在 application.yml 中进行配置
     * 2、通过 Java 代码配置在内存中
     * 3、通过 Java 从数据库中加载
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().  //开启内存中定义用户
                withUser("mbql").   //用户名
                password("123").    //密码
                roles("admin");     //角色
    }

    /**
     * 忽略静态资源的拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }

    /**
     * 配置用户认证和鉴权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()    //对应了xml中的<intercept-url>
                .antMatchers("/hello").hasRole("admin") //表示访问 /hello 这个接口，需要具备 admin 这个角色
                .anyRequest().authenticated()   //表示剩余的其他接口，登录之后就能访问
                .and().formLogin()  //对应xml中的<fromLogin>
                //定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
                .loginPage("/login.html")
                .permitAll()    //表示登录相关页面不被拦截
                .and()          //开启新的一轮HttpSecurity轮回
                .csrf().disable();  //关闭csrf请求
    }
}
