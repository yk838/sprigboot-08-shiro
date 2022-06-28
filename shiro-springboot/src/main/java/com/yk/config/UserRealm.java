package com.yk.config;

import com.yk.pojo.Student;
import com.yk.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add");
        Subject subject = SecurityUtils.getSubject();
        Student currentStudent = (Student)subject.getPrincipal();// 拿到Student 对象
        // 设置对象权限
        info.addStringPermission(currentStudent.getPerm());
        return info;

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
        return new SimpleAuthenticationInfo(student,student.getPwd(),"");
    }
}
