package main.java.edu.nju.onlinestock.factory;

import main.java.edu.nju.onlinestock.dao.ResultDao;
import main.java.edu.nju.onlinestock.dao.StudentDao;
import main.java.edu.nju.onlinestock.dao.impl.ResultDaoImpl;
import main.java.edu.nju.onlinestock.dao.impl.StudentDaoImpl;

/**
 * Created by echo on 16/12/28.
 */
public class DaoFactory {
    public static ResultDao getResultDao(){
        return ResultDaoImpl.getInstance();
    }

    public static StudentDao getStudentDao(){
        return StudentDaoImpl.getInstance();
    }
}
