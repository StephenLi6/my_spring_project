package com.itheima.test;

import com.itheima.dao.ITeacherDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/1620:26
 * com.itheimaMyDailyProject
 */
public class TeacherTest {

    private InputStream in ;
    private SqlSession sqlSession;
    private ITeacherDao iTeacherDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession(true);
        iTeacherDao = sqlSession.getMapper(ITeacherDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.close();
        in.close();
    }

    /*
    * Just do it
    * Desinger:TheShy
    *測試一對一關聯
    */
    @Test
    public void testFindAll(){
        System.out.println(iTeacherDao.testFindAll());
    }

    @Test
    public void testFindAllCourse(){
        System.out.println(iTeacherDao.testFindAllCourse());
    }

    @Test
    public void testFindTeacherCourse(){
        System.out.println(iTeacherDao.testFindTeacherCourse());
    }
}
