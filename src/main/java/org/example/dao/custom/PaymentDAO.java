package org.example.dao.custom;

import javafx.scene.chart.XYChart;
import org.example.dao.CrudDAO;
import org.example.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment> {
    public  XYChart.Series IncomeChart(XYChart.Series chart);
    public  String generateNewId() throws SQLException, ClassNotFoundException;
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException;
    public Payment paymentIdCheck(String paymentID) throws SQLException, ClassNotFoundException;
    public boolean update(String paymentID, String amount, String date, String studentID, String userID, String courseID) throws SQLException, ClassNotFoundException;
    boolean savePayment(Payment payment) throws SQLException;
}
