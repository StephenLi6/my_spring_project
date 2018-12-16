package com.itheima.dao;

import com.itheima.domain.Course;
import com.itheima.domain.Teacher;

import java.util.List;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/1620:42
 * com.itheima.daoMyDailyProject
 */
public interface ITeacherDao {
    List<Teacher> testFindAll();

    List<Course> testFindAllCourse();

    List<Teacher> testFindTeacherCourse();
}
