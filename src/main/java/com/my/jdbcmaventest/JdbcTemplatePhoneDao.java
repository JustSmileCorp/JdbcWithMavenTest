package com.my.jdbcmaventest;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 19.11.14
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
public class JdbcTemplatePhoneDao implements PhoneDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplatePhoneDao() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root"); //sdfsdfsdf
        dataSource.setURL("jdbc:mysql://localhost/basephone?characterEncoding=utf8");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void savePhone(Phone phone) {
        jdbcTemplate.update("insert into basephone.phone(id, name, price1) values(?, ?, ?)",
                phone.getId(), phone.getName(), phone.getPrice());
    }

    @Override
    public void updatePhone(Phone phone) {
        jdbcTemplate.update("update basephone.phone set name = ?, price = ? WHERE id = ?",
                phone.getName(), phone.getPrice(), phone.getId());
    }

    @Override
    public List<Phone> getAllPhones() {
        return jdbcTemplate.query("select * from basephone.phone", new PhoneRowMapper());
    }

    @Override
    public Phone getPhoneById(long id) {
        List<Phone> phones = jdbcTemplate.query("select * from basephone.phone", new PhoneRowMapper());
        if(phones.isEmpty()){
            return null;
        }
        return phones.get(0);
    }

    private class PhoneRowMapper implements RowMapper<Phone> {
        @Override
        public Phone mapRow(ResultSet rs, int rowNum) throws SQLException {
            Phone phone = new Phone();
            phone.setId(rs.getLong("id"));
            phone.setName(rs.getString("name"));
            phone.setPrice(rs.getDouble("price"));
            return phone;
        }
    }
}
