package com.my.jdbcmaventest;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 14.11.14
 * Time: 0:19
 * To change this template use File | Settings | File Templates.
 */
public interface PhoneDao {
    void savePhone(Phone phone);
    void updatePhone(Phone phone);
    List<Phone> getAllPhones();
    Phone getPhoneById(long id);


}
