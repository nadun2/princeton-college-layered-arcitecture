package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.LecturerDAO;
import org.example.entity.Lecturer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LecturerDAOImpl implements LecturerDAO {
    public ArrayList<Lecturer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Lecturer> allLecturer = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM lecturer");

            while (rst.next()) {
                Lecturer lecturer = new Lecturer(rst.getString("lecturer_id"), rst.getString("name"), rst.getString("contact_no"), rst.getString("address"), rst.getString("nic_no"));
                allLecturer.add(lecturer);
            }

        return allLecturer;
    }

    public boolean add(Lecturer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO lecturer VALUES(?,?,?,?,?)", entity.getLecturerID(), entity.getName(), entity.getContactNo(), entity.getAddress(), entity.getNicNo());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM lecturer WHERE lecturer_id = ?", id);
    }
    public boolean update(Lecturer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE lecturer SET name = ?,contact_no = ?,address = ?,nic_no = ?  WHERE lecturer_id = ?", entity.getName(), entity.getContactNo(), entity.getAddress(), entity.getNicNo(), entity.getLecturerID());
    }

    @Override
    public boolean generateNewID() throws SQLException, ClassNotFoundException {
        return false;
    }
}
