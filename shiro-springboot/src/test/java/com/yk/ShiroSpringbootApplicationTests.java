package com.yk;

import com.yk.pojo.Student;
import com.yk.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private StudentService studentService;
    @Test
    public void test() {
        Student student = studentService.queryStudentByName("yk");
        System.out.println(student);
    }
}
