package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.BatchDAO;
import org.example.entity.Batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BatchDAOImpl implements BatchDAO {
    public ArrayList<Batch> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Batch> allBatch = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM batch");
        while (rst.next()) {
            Batch batch = new Batch(rst.getString("batch_id"), rst.getString("batch_name"), rst.getString("no_of_students"), rst.getString("no_of_lecturers"), rst.getString("main_lecturer"), rst.getString("batch_representer"));
            allBatch.add(batch);
        }
        return allBatch;
    }
    public boolean add(Batch entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO batch VALUES(?,?,?,?,?,?)", entity.getBatchID(), entity.getBatchName(), entity.getNoOfStudents(), entity.getNoOfLecturers(), entity.getMainLecturer(), entity.getBatchReprsenter());
    }
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM batch WHERE batch_id = ?", id);

    }
    public  boolean update(Batch entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE batch SET batch_name = ?, no_of_students = ?, no_of_lecturers = ?,main_lecturer = ?, batch_representer = ?  WHERE batch_id = ?", entity.getBatchName(), entity.getNoOfStudents(), entity.getNoOfLecturers(), entity.getMainLecturer(), entity.getBatchReprsenter(), entity.getBatchID());
    }

    @Override
    public boolean generateNewID() throws SQLException, ClassNotFoundException {
        return false;
    }


}
