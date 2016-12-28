package main.java.edu.nju.onlinestock.beans;

import main.java.edu.nju.onlinestock.model.Result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by echo on 16/12/28.
 */
public class ResultListBean implements Serializable {
    private List<Result> studentResultList;

    public List<Result> getStudentResultList(){ return this.studentResultList; }

    public void setStudentResultList(List<Result> studentResultList){ this.studentResultList = studentResultList; }

    public Result getStudentResult(int index){ return this.studentResultList.get(index); }
}
