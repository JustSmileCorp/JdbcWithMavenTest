package com.my.jdbcmaventest;
import com.google.common.base.Throwables;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 14.11.14
 * Time: 0:24
 * To change this template use File | Settings | File Templates.
 */
public class PhoneManager {
    public static void main(String[] args) {
        PhoneDao phoneDao = new JdbcTemplatePhoneDao();
//добавление в бд
        Phone phone = new Phone();
        phone.setId(123456);
        phone.setName("Fly");
        phone.setPrice(15.54);

        try{
            phoneDao.savePhone(phone);
        } catch (DuplicateKeyException exception){
            System.out.println("Телефон с ИД = " + phone.getId() +" уже есть в БД!");
        } catch(Exception ex){
            System.out.println("Лажа! " + Throwables.getStackTraceAsString(ex));
        }

//вывод таблицы бд
        List<Phone> allPhones = phoneDao.getAllPhones();
        print(allPhones);
//обновление записи
        phone.setName("Fly2");
        phoneDao.updatePhone(phone);
        Phone phoneById = phoneDao.getPhoneById(phone.getId()); //получение по полю id(сделать id ключем)
        print(phoneById);
    }

    private static void print(Phone phone) {
        //вывод одного телефона
        System.out.println("-----------------");
        System.out.println(phone);
        System.out.println("-----------------");dfdf
    }

    private static void print(List<Phone> allPhones) {
        //вывод всех телефонов
        for(Phone phone: allPhones){
            System.out.println("***********");
            System.out.println(phone);
            System.out.println("***********");
        }
    }
   }
