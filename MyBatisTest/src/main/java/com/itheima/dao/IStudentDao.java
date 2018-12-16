package com.itheima.dao;

import com.itheima.domain.Student;

import java.util.List;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/1620:25
 * com.itheima.daoMyDailyProject
 */
public interface IStudentDao {
    List<Student> testFindAll();

    List<Student> testFindStudentCourse();
}
