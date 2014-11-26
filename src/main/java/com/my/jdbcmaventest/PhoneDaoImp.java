package com.my.jdbcmaventest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 14.11.14
 * Time: 0:23
 * To change this template use File | Settings | File Templates.
 */
public class PhoneDaoImp implements PhoneDao {


    @Override
    public void savePhone(Phone phone) {
        Connection con = null;
        try {
            con = openConnection();
            String insertTableSQL = "insert into basephone.phone "
                    + "(id, name, price) values(?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setLong(1, phone.getId());
            preparedStatement.setString(2, phone.getName());
            preparedStatement.setDouble(3, phone.getPrice());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
    }

    private void closeConnection(Connection connection) {
        //Обязательно необходимо закрыть соединение
        try {
            if (connection != null)
                connection.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Connection openConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/basephone?characterEncoding=utf8",
                "root", "root");
        System.out.println("Connected");
        return connection;
    }

    @Override
    public void updatePhone(Phone phone) {
        Connection con = null;
        try {
            con = openConnection();
            String updateTableSQL = "update basephone.phone set name = ? and price = ? where id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(updateTableSQL);
            preparedStatement.setString(1, phone.getName());
            preparedStatement.setDouble(2, phone.getPrice());
            preparedStatement.setLong(3, phone.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public List<Phone> getAllPhones() {
        List<Phone> phones = new ArrayList<Phone>();
        Connection con = null;
        try {
            con = openConnection();
            Statement st = con.createStatement();
            String query = "select * from phone";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Phone phone = new Phone();
                String n = rs.getString("name");
                phone.setName(n);
                Long i = rs.getLong("id");
                phone.setId(i);
                Double p = rs.getDouble("price");
                phone.setPrice(p);
                phones.add(phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return phones;
    }

    @Override
    public Phone getPhoneById(long id) {
        Connection con = null;
        try {
            con = openConnection();
            String showPhoneById = "select * from basephone.phone where id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(showPhoneById);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Phone phone = new Phone();
                String n = rs.getString("name");
                phone.setName(n);
                Long i = rs.getLong("id");
                phone.setId(i);
                Double p = rs.getDouble("price");
                phone.setPrice(p);
                return phone;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            {
                closeConnection(con);
            }
        }
        return null;
    }
}
