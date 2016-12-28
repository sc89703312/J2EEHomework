package main.java.edu.nju.onlinestock.service.impl;

import main.java.edu.nju.onlinestock.factory.DaoFactory;
import main.java.edu.nju.onlinestock.model.Student;
import main.java.edu.nju.onlinestock.service.StudentService;

/**
 * Created by echo on 16/12/28.
 */
public class StudentServiceImpl implements StudentService {

    private static StudentService studentService = new StudentServiceImpl();

    public static StudentService getInstance(){ return studentService; }

    private StudentServiceImpl(){}

    @Override
    public Student findStudentById(String studentId) {
        return DaoFactory.getStudentDao().findStudentById(studentId);
    }
}
