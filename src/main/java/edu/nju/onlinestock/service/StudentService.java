package main.java.edu.nju.onlinestock.service;

import main.java.edu.nju.onlinestock.model.Student;

/**
 * Created by echo on 16/12/28.
 */
public interface StudentService {
    Student findStudentById(String studentId);
}
