
package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.Data.Account;
import com.Data.Alert;
import com.Data.Transaction;
import com.Data.User;

public class FraudService {
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public int logic1_save(User u) {

        int result = 0;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fraudshield_users",
                "root",
                 DB_PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(first_name, last_name, email, password_hash, age, gender, account_number, address) VALUES(?,?,?,?,?,?,?,?)"
            );

            ps.setString(1, u.getFirstName());
            ps.setString(2, u.getLastName());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setInt(5, u.getAge());
            ps.setString(6, u.getGender());
            ps.setString(7, u.getAccountNumber());
            ps.setString(8, u.getAddress());

            result = ps.executeUpdate();

            System.out.println("Registration inserted: " + result);

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int logic2_login(User u) {

        int result = 0;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fraudshield_users",
                "root",
                 DB_PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=? AND password_hash=?"
            );

            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = 1;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Account logic3_account(Account a) {

        Account result = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fraudshield_users",
                "root",
                 DB_PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=?"
            );

            ps.setString(1, a.getEmail());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                result = new Account();

                result.setFirstName(rs.getString("first_name"));
                result.setLastName(rs.getString("last_name"));
                result.setEmail(rs.getString("email"));
                result.setAge(rs.getInt("age"));
                result.setGender(rs.getString("gender"));
                result.setAccountNumber(rs.getString("account_number"));
                result.setAddress(rs.getString("address"));
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int logic4_save(Transaction t) {

        int result = 0;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fraudshield_transactions",
                "root",
                 DB_PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO transactions " +
                "(email, account_number, transaction_type, amount, " +
                "receiver_account, description, transaction_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setString(1, t.getEmail());
            ps.setString(2, t.getAccountNumber());
            ps.setString(3, t.getTransactionType());
            ps.setDouble(4, t.getAmount());
            ps.setString(5, t.getReceiverAccount());
            ps.setString(6, t.getDescription());
            ps.setString(7, t.getTransactionStatus());

            result = ps.executeUpdate();

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int logic5_saveHistory(Transaction t) {

        int result = 0;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fraudshield_transactions",
                "root",
                 DB_PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO transaction_history " +
                "(email, account_number, transaction_type, amount, " +
                "receiver_account, description, transaction_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setString(1, t.getEmail());
            ps.setString(2, t.getAccountNumber());
            ps.setString(3, t.getTransactionType());
            ps.setDouble(4, t.getAmount());
            ps.setString(5, t.getReceiverAccount());
            ps.setString(6, t.getDescription());
            ps.setString(7, t.getTransactionStatus());

            result = ps.executeUpdate();

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // View Transaction History using Account Number

    public List<Transaction> logic6_viewHistory(String accountNumber) {

        List<Transaction> list = new ArrayList<>();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fraudshield_transactions",
                "root",
                 DB_PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM transaction_history WHERE account_number = ? ORDER BY history_id DESC"
            );

            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Transaction t = new Transaction();

                t.setTransactionId(rs.getInt("history_id"));
                t.setEmail(rs.getString("email"));
                t.setAccountNumber(rs.getString("account_number"));
                t.setTransactionType(rs.getString("transaction_type"));
                t.setAmount(rs.getDouble("amount"));
                t.setReceiverAccount(rs.getString("receiver_account"));
                t.setDescription(rs.getString("description"));
                t.setTransactionStatus(rs.getString("transaction_status"));
                t.setTransactionDate(rs.getString("transaction_date"));

                list.add(t);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Save Customer Alert
    public int logic7_saveAlert(Alert a) {

        int result = 0;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fraudshield_transactions",
                "root",
                 DB_PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO customer_alerts " +
                "(transaction_id, alert_type, risk_level, description, alert_status) " +
                "VALUES (?, ?, ?, ?, ?)"
            );

            ps.setInt(1, a.getTransactionId());
            ps.setString(2, a.getAlertType());
            ps.setString(3, a.getRiskLevel());
            ps.setString(4, a.getDescription());
            ps.setString(5, a.getAlertStatus());

            result = ps.executeUpdate();

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    // View Customer Alerts
    public List<Alert> logic8_viewAlerts() {

        List<Alert> alerts = new ArrayList<>();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fraudshield_transactions",
                "root",
                 DB_PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM customer_alerts ORDER BY alert_id DESC"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Alert a = new Alert();

                a.setAlertId(rs.getInt("alert_id"));
                a.setTransactionId(rs.getInt("transaction_id"));
                a.setAlertType(rs.getString("alert_type"));
                a.setRiskLevel(rs.getString("risk_level"));
                a.setDescription(rs.getString("description"));
                a.setAlertStatus(rs.getString("alert_status"));

                Timestamp date = rs.getTimestamp("alert_date");

                if (date != null) {
                    a.setAlertDate(date.toString());
                }

                alerts.add(a);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alerts;
    }

    // View Customer Alerts by Account Number
    public List<Alert> logic8_viewAlerts(String accountNumber) {

        List<Alert> alerts = new ArrayList<>();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fraudshield_transactions",
                "root",
                 DB_PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM customer_alerts " +
                "WHERE account_number = ? " +
                "ORDER BY alert_id DESC"
            );

            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Alert a = new Alert();

                a.setAlertId(rs.getInt("alert_id"));
                a.setTransactionId(rs.getInt("transaction_id"));
                a.setAccountNumber(rs.getString("account_number"));
                a.setAlertType(rs.getString("alert_type"));
                a.setRiskLevel(rs.getString("risk_level"));
                a.setDescription(rs.getString("description"));
                a.setAlertStatus(rs.getString("alert_status"));

                Timestamp date = rs.getTimestamp("alert_date");

                if (date != null) {
                    a.setAlertDate(date.toString());
                }

                alerts.add(a);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alerts;
    }


}
