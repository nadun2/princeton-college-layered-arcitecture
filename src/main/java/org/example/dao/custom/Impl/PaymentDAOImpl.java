package org.example.dao.custom.Impl;

import javafx.scene.chart.XYChart;
import org.example.dao.SQLUtil;
import org.example.dao.custom.PaymentDAO;
import org.example.db.DbConnection;
import org.example.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    public static boolean savePayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment (payment_id, amount, date, student_id, user_id, course_id) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement checkStmt = connection.prepareStatement(sql);
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, payment.getPaymentID());
        pstm.setString(2, payment.getAmount());
        pstm.setString(3, payment.getDate());
        pstm.setString(4, payment.getStudentID());
        pstm.setString(5, payment.getUserID());
        pstm.setString(6, payment.getCourseID());
        return pstm.executeUpdate() > 0;
    }

    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allPayments = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment");

        while (rst.next()) {
            Payment payment = new Payment(rst.getString("payment_id"), rst.getString("amount"), rst.getString("date"), rst.getString("student_id"), rst.getString("user_id"), rst.getString("course_id"));
            allPayments.add(payment);
        }

        return allPayments;
    }

    public boolean add(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment VALUES(?,?,?,?,?,?)", entity.getPaymentID(), entity.getAmount(), entity.getDate(), entity.getStudentID(), entity.getUserID(), entity.getCourseID());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM payment WHERE payment_id = ?", id);
    }
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE payment SET amount = ?, date = ?, student_id = ?,user_id = ?, course_id = ?  WHERE payment_id = ?", entity.getAmount(), entity.getDate(), entity.getStudentID(), entity.getUserID(), entity.getCourseID(), entity.getPaymentID());
    }

    @Override
    public boolean generateNewID() throws SQLException, ClassNotFoundException {
        return false;
    }

    public static String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1");
        if (rst.next()) {
            int id = Integer.parseInt(rst.getString("payment_id"));
            id++;
            return rst.getString(1).split("-")[1];
        }

        return "P-001";
    }
    public static XYChart.Series IncomeChart(XYChart.Series chart) {
        String sql = "SELECT date , SUM(amount) FROM payment GROUP BY date ORDER BY TIMESTAMP(date)";

        try {
            ResultSet rst = org.example.db.DbConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();

            while (rst.next()) {
                chart.getData().add(new XYChart.Data<>(rst.getString(1), rst.getFloat(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chart;
    }
}
