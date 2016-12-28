package main.java.edu.nju.onlinestock.service.impl;

import main.java.edu.nju.onlinestock.factory.DaoFactory;
import main.java.edu.nju.onlinestock.model.Result;
import main.java.edu.nju.onlinestock.service.ResultService;

import java.util.List;

/**
 * Created by echo on 16/12/28.
 */
public class ResultServiceImpl implements ResultService{

    private static ResultService resultService = new ResultServiceImpl();

    public static ResultService getInstance(){ return resultService; }

    private ResultServiceImpl(){}

    @Override
    public List<Result> getStudentResultList(String studentId) {
        return DaoFactory.getResultDao().findResultListById(studentId);
    }
}
