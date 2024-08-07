package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.CoursesDAO;
import org.example.entity.Courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CoursesDAOImpl implements CoursesDAO {
    public ArrayList<Courses> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Courses> allCourses = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM course");



            while (rst.next()) {
                Courses course = new Courses(rst.getString("course_id"), rst.getString("course_name"), rst.getString("duration"), rst.getString("main_lecturer"), rst.getString("course_fee"));
                allCourses.add(course);
            }
              return allCourses;
    }
    public  boolean add(Courses entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO course VALUES(?,?,?,?,?)", entity.getCourseID(), entity.getCourseName(), entity.getCourseDuration(), entity.getMainLecturer(), entity.getCourseFee());
    }
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM course WHERE course_id = ?", id);
    }
    public boolean update(String courseID, String courseName, String courseDuration, String mainLecturer, String courseFee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE course SET course_name = ?, duration = ?, main_lecturer = ?, course_fee = ?  WHERE course_id = ?", courseName, courseDuration, mainLecturer, courseFee, courseID);
    }

    public Courses courseIDCheck(String courseID) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM course WHERE course_id = ?", courseID);
        if (rst.next()) {
            return new Courses(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
        } else {
            return null; // Return null if no course is found
        }
    }

    @Override
    public boolean generateNewID() throws SQLException, ClassNotFoundException {
        return false;
    }
}


