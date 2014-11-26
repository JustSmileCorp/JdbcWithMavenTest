package com.my.jdbcmaventest;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 14.11.14
 * Time: 0:20
 * To change this template use File | Settings | File Templates.
 */
public class Phone {
    private long id;
    private String name;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }


}
