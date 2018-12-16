package com.itheima.domain;

import java.util.List;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/1620:39
 * com.itheima.domainMyDailyProject
 */
public class Teacher {
    private String tno;
    private String tname;

    List<Course> list;


    public List<Course> getList() {
        return list;
    }

    public void setList(List<Course> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tno='" + tno + '\'' +
                ", tname='" + tname + '\'' +
                ", list=" + list +
                '}';
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
