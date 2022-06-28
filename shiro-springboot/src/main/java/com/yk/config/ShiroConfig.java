package com.yk.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
    //3. ShiroFilterFactoryBean
    @Bean(name="shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("SecurityManager")DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //实现shiro 的内置过滤器
        /**
         *    anon:无需认证就可以访问
         *              authc:必须认证才可访问
         *              user：必须拥有 "记住我"功能才能用
         *              perms：拥有对某个资源的权限才能访问
         *              role:拥有某个角色权限才能访问
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/user/add","anon");//表示/user/add这个请求所有人可以访问
        // 授权  正常情况下，没有授权跳转到未授权页面
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        // 拦截
        filterMap.put("/user/*","authc");//表示/user/update这个请求只有登录后可以访问

        //设置登录的请求
        bean.setLoginUrl("/toLogin");
        // 未授权页面
        bean.setUnauthorizedUrl("/unauthorized");

        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }
    // 2.DefaultwebSecurityManager
    @Bean(name="SecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    // 1，创建realm 对象，需要自定义类 我们写个UserRealm类
    @Bean(name="userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }
    //整合ShiroDialect，用来整合shiro和thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
