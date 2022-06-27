package com.yk.mapper;

import com.yk.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentMapper {
    public Student queryStudentByName(String name);
}
