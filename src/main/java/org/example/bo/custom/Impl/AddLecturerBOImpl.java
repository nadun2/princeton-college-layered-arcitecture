package org.example.bo.custom.Impl;

import org.example.bo.custom.AddLecturerBO;
import org.example.dao.custom.Impl.LecturerDAOImpl;
import org.example.dao.custom.LecturerDAO;
import org.example.entity.Lecturer;

import java.sql.SQLException;

public class AddLecturerBOImpl implements AddLecturerBO {
   LecturerDAO lecturerDAO = new LecturerDAOImpl();
    public boolean add(Lecturer lecturer) throws SQLException, ClassNotFoundException {
        return lecturerDAO.add(lecturer);

    }
    public Lecturer lecturerIdCheck(String lecturerID) throws SQLException, ClassNotFoundException{
        return lecturerDAO.lecturerIdCheck(lecturerID);
    }
    }

