package com.itheima.test;

import com.itheima.dao.IStudentDao;
import org.apache.ibatis.io.Resources;
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
public class StudentTest {

    private InputStream in ;
    private SqlSession sqlSession;
    private IStudentDao iStudentDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession(true);
        iStudentDao = sqlSession.getMapper(IStudentDao.class);
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
        System.out.println(iStudentDao.testFindAll());
    }

    @Test
    public void testFindStudentCourse(){
        System.out.println(iStudentDao.testFindStudentCourse());
    }
}
