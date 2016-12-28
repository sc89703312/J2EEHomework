package main.java.edu.nju.onlinestock.dao;

import main.java.edu.nju.onlinestock.model.Result;

import java.util.List;

/**
 * Created by echo on 16/12/28.
 */
public interface ResultDao {

    List<Result> findResultListById(String id);

}
