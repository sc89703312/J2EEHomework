package main.java.edu.nju.onlinestock.factory;

import main.java.edu.nju.onlinestock.service.ResultService;
import main.java.edu.nju.onlinestock.service.StudentService;
import main.java.edu.nju.onlinestock.service.impl.ResultServiceImpl;
import main.java.edu.nju.onlinestock.service.impl.StudentServiceImpl;

/**
 * Created by echo on 16/12/28.
 */
public class ServiceFactory {

    public static ResultService getResultService(){
        return ResultServiceImpl.getInstance();
    }

    public static StudentService getStudentService(){
        return StudentServiceImpl.getInstance();
    }

}
