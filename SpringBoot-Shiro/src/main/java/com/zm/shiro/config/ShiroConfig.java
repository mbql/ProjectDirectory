package com.zm.shiro.config;

import com.zm.shiro.shiro.CustomRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mbql
 * @date 2020/2/23 17:00
 */
@Configuration
public class ShiroConfig {

    /**
     * 不加这个注解不生效，具体不详
     *
     * @return advisorAutoProxyCreator
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 将自己定义的验证方式加入到容器中
     *
     * @return customRealm
     */
    @Bean
    public CustomRealm customRealm() {
        CustomRealm realm = new CustomRealm();
        return realm;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     *
     * @return securityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customRealm());
        return defaultWebSecurityManager;
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @param securityManager
     * @return shiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> hashMap = new HashMap<>();
        //注销
        hashMap.put("/logout", "logout");
        //对所有用户认证
        hashMap.put("/**", "authc");
        //登录
        filterFactoryBean.setLoginUrl("/login");
        //首页
        filterFactoryBean.setSuccessUrl("/index");
        //错误页面，鉴权不通过返回
        filterFactoryBean.setUnauthorizedUrl("/error");
        filterFactoryBean.setFilterChainDefinitionMap(hashMap);
        return filterFactoryBean;
    }

    /**
     * 加入注解的使用，不加入这个注解不生效
     *
     * @param securityManager
     * @return authorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }

}
