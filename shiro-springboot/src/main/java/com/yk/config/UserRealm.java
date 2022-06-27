package com.yk.config;

import com.yk.pojo.Student;
import com.yk.service.StudentService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        return null;
    }

    @Resource
    private StudentService studentService;
    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");
//        String name = "root";
//        String password = "123456";

        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        // 真实数据库数据
        Student student = studentService.queryStudentByName(userToken.getUsername());
//        if (!userToken.getUsername().equals(name)) {//用户名认证
//            return null;//抛出异常UnknownAccountException
//        }
        if (student == null) {
            return null;//抛出异常UnknownAccountException
        }
        //密码认证，shiro做
        return new SimpleAuthenticationInfo("",student.getPwd(),"");
    }
}
