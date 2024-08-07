package org.example.dao.custom.Impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.EmployesDAO;
import org.example.entity.Employes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployesDAOImpl implements EmployesDAO {
    public ArrayList<Employes> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employes> allEmployees = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        while (rst.next()) {
            Employes employee = new Employes(rst.getString("employee_id"), rst.getString("name"), rst.getString("contact_no"), rst.getString("address"), rst.getString("nic_no"));
            allEmployees.add(employee);
        }
        return allEmployees;
    }

    public boolean add(Employes entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?,?,?,?,?)", entity.getEmployeeID(), entity.getName(), entity.getContactNo(), entity.getAddress(), entity.getNicNo());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE employee_id = ?", id);
    }
    public boolean update(String employeeID, String name, String contactNo, String address, String nicNo) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET employee_id = ?,name = ?,contact_no = ?,address = ?,nic_no = ?  WHERE employee_id = ?", employeeID, name, contactNo, address, nicNo, employeeID);
    }
    public Employes employeeIdCheck(String employeeID) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee WHERE employee_id = ?", employeeID);
        if (rst.next()) {
            return new Employes(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
        } else {
            return null; // Return null if no course is found
        }
    }
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) AS employee_count FROM employee");
        int employeeCount = 0;
        if (rst.next()) {
            employeeCount = rst.getInt("employee_count");
        }
        return employeeCount;
    }


    @Override
    public boolean generateNewID() throws SQLException, ClassNotFoundException {
        return false;
    }
}
