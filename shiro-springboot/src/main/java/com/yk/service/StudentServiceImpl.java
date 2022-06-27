package com.yk.service;

import com.yk.mapper.StudentMapper;
import com.yk.pojo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService{
    @Resource
    private StudentMapper studentMapper;
    @Override
    public Student queryStudentByName(String name) {
        return studentMapper.queryStudentByName(name);
    }
}
