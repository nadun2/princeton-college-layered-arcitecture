package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Batch;

import java.sql.SQLException;

public interface BatchDAO extends CrudDAO<Batch> {
    public int getBatchCount() throws SQLException, ClassNotFoundException;
    public boolean update(String batchId, String batchName, String noOfStudents, String noOfLecturers, String mainLecturer, String batchRepresenter) throws SQLException, ClassNotFoundException;
    public Batch batchIdCheck(String batchID) throws SQLException, ClassNotFoundException;
}

