package main.java.edu.nju.onlinestock.dao.impl;

import main.java.edu.nju.onlinestock.dao.ConnectionHelper;
import main.java.edu.nju.onlinestock.dao.StudentDao;
import main.java.edu.nju.onlinestock.model.Exam;
import main.java.edu.nju.onlinestock.model.Result;
import main.java.edu.nju.onlinestock.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by echo on 16/12/28.
 */
public class StudentDaoImpl implements StudentDao {

    private static StudentDaoImpl studentDao = new StudentDaoImpl();
    private static ConnectionHelper connection = ConnectionImpl.getConnectionInstance();

    private StudentDaoImpl(){}

    public static StudentDaoImpl getInstance(){ return studentDao; }

    @Override
    public Student findStudentById(String id) {
        Connection con=connection.getConnection();
        PreparedStatement stmt=null;
        ResultSet result=null;
        Student student = null;
        try
        {
            stmt=con.prepareStatement("select * from student where id = ?");
            stmt.setString(1, id);
            result=stmt.executeQuery();
            if(result.next()){
                student = new Student();
                student.setId(result.getInt("id"));
                student.setGrade(result.getString("grade"));
                student.setName(result.getString("name"));
            }else{

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
        return student;
    }
}
