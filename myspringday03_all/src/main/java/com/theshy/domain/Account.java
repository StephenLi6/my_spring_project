package com.theshy.domain;

import java.io.Serializable;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2819:21
 * com.theshy.domainMyDailyProject
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 7355810572012650248L;

    private Integer id;
    private String name;
    private Double money;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getMoney() {
        return money;
    }
    public void setMoney(Double money) {
        this.money = money;
    }
}
