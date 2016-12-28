package main.java.edu.nju.onlinestock.dao.impl;

import main.java.edu.nju.onlinestock.dao.ConnectionHelper;
import main.java.edu.nju.onlinestock.dao.ResultDao;
import main.java.edu.nju.onlinestock.model.Exam;
import main.java.edu.nju.onlinestock.model.Result;
import main.java.edu.nju.onlinestock.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by echo on 16/12/28.
 */
public class ResultDaoImpl implements ResultDao {

    private static ResultDaoImpl resultDao = new ResultDaoImpl();
    private static ConnectionHelper connection = ConnectionImpl.getConnectionInstance();

    private ResultDaoImpl(){}

    public static ResultDaoImpl getInstance(){ return resultDao; }

    @Override
    public List<Result> findResultListById(String id) {
        Connection con=connection.getConnection();
        PreparedStatement stmt=null;
        ResultSet result=null;
        ArrayList<Result> list=new ArrayList<>();
        try
        {
            stmt=con.prepareStatement("select * from result R,exam E,student S where R.student_id = ? and E.id = R.exam_id and R.student_id = S.id");
            stmt.setString(1, id);
            result=stmt.executeQuery();
            while(result.next())
            {
                Result studentResult = new Result();
                Exam studentExam = new Exam();
                Student student = new Student();

                studentExam.setId(result.getInt("E.id"));
                studentExam.setName(result.getString("E.name"));

                student.setId(result.getInt("S.id"));
                student.setName(result.getString("S.name"));
                student.setGrade(result.getString("S.grade"));

                studentResult.setId(result.getInt("R.id"));
                studentResult.setResult(result.getInt("R.result"));
                studentResult.setStudent_id(result.getInt("R.student_id"));
                studentResult.setExam_id(result.getInt("R.exam_id"));
                studentResult.setExam(studentExam);
                studentResult.setStudent(student);

                list.add(studentResult);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            connection.closeConnection(con);
            connection.closePreparedStatement(stmt);
            connection.closeResult(result);
        }
        return list;
    }


}
