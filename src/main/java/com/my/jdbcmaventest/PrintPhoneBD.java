package com.my.jdbcmaventest;

import com.google.common.base.Strings;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 13.11.14
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
public class PrintPhoneBD {
    private Connection con;

    public PrintPhoneBD() {
                try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/basephone?characterEncoding=utf8",
                    "root", "root");
            System.out.println("Connected");
            Statement st = con.createStatement();
            String query = "select * from phone";
            ResultSet rs = st.executeQuery(query);
            Phone phone = new Phone();
                    System.out.println(rs);
            System.out.println("Disconnected");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printResult(ResultSet rs) throws SQLException {
        String name;
        int price, id;
        while (rs.next()) {
            name = rs.getString("name");
            price = rs.getInt("price");
            id = rs.getInt("id");
            System.out.println("--------------------");
            System.out.println("name: " + name);
            System.out.println("price: " + price);
            System.out.println("id: " + id);

        }
    }

    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver sql download!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PrintPhoneBD printPhoneBD = new PrintPhoneBD();
    }


}
