package org.example.bo.custom.Impl;

import org.example.bo.custom.AddExamBO;
import org.example.dao.custom.ExamsDAO;
import org.example.dao.custom.Impl.ExamsDAOImpl;
import org.example.entity.Exams;

import java.sql.SQLException;

public class AddExamBOImpl implements AddExamBO {
    ExamsDAO examDAO = new ExamsDAOImpl();
    public boolean add(Exams exam) throws SQLException, ClassNotFoundException {
        try {
            return examDAO.add(exam);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Exams examIdCheck(String examID) throws SQLException, ClassNotFoundException {
        return examDAO.examIdCheck(examID);
    }
    }

