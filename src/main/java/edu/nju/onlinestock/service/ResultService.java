package main.java.edu.nju.onlinestock.service;

import main.java.edu.nju.onlinestock.model.Result;

import java.util.List;

/**
 * Created by echo on 16/12/28.
 */
public interface ResultService {
    List<Result> getStudentResultList(String studentId);
}
