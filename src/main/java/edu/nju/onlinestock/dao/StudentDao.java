package main.java.edu.nju.onlinestock.dao;

import main.java.edu.nju.onlinestock.model.Student;

/**
 * Created by echo on 16/12/28.
 */
public interface StudentDao {
    Student findStudentById(String id);
}
