package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.ScheduleDAO;
import org.example.entity.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleDAOImpl implements ScheduleDAO {
    public ArrayList<Schedule> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Schedule> allSchedule = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM schedule");

            while (rst.next()) {
                Schedule schedule = new Schedule(
                        rst.getString("schedule_id"),
                        rst.getString("module_name"),
                        rst.getString("date"),
                        rst.getString("time"),
                        rst.getString("lecturer_id")
                );
                allSchedule.add(schedule);
            }

        return allSchedule;
    }

    public boolean add(Schedule entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO schedule VALUES(?,?,?,?,?)", entity.getScheduleID(), entity.getModuleName(), entity.getDate(), entity.getTime(), entity.getLecturerID());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM schedule WHERE schedule_id = ?", id);
    }
    public  boolean update(String scheduleID, String moduleName, String date, String time, String lecturerID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE schedule SET  schedule_id = ?,module_name = ?, date = ?, time = ?,lecturer_id = ?,WHERE schedule_id = ?", scheduleID, moduleName, date, time, lecturerID, scheduleID);
    }
    public Schedule checkScheduleId(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM schedule WHERE schedule_id = ?", id);
        if (rst.next()) {
            return new Schedule(
                    rst.getString("schedule_id"),
                    rst.getString("module_name"),
                    rst.getString("date"),
                    rst.getString("time"),
                    rst.getString("lecturer_id")
            );
        }
        return null;
    }

    @Override
    public boolean generateNewID() throws SQLException, ClassNotFoundException {
        return false;
    }
}
