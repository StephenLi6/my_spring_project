package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyBatisTest {
    private InputStream in ;
    private SqlSession sqlSession;
    private IUserDao iUserDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession(true);
        iUserDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.close();
        in.close();
    }

    @Test
    public void findAll() {
        List<User> users = iUserDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("Auto Commit");
        user.setAddress("廣州市新塘市");
        user.setBirthday(new Date());
        user.setSex("男");
        iUserDao.saveUser(user);
        System.out.println(user);

    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(51);
        user.setSex("女");
        user.setBirthday(new Date());
        user.setAddress("湖北省襄陽市");
        user.setUsername("GoGoing");
        iUserDao.update(user);
    }

    @Test
    public void testDelete(){
        iUserDao.delete(51);
    }


    /*
    * Just do it
    * Desinger:TheShy
    */
    @Test
    public void findById(){
        System.out.println(iUserDao.findById(51));
    }

    @Test
    public void findByName(){
        System.out.println(iUserDao.findByName("T"));
    }

    /*
    * Just do it
    * Desinger:TheShy
    * 聚合函數查找總個數
    */
    @Test
    public void testFindTotal(){
        List<String> list = new ArrayList<String>();
        System.out.println(iUserDao.findTotal());
    }

    /*
    * Just do it
    * Desinger:TheShy
    * 作業題，id大於43，小於等於48
    */
    @Test
    public void findRange(){
        QueryVo queryVo = new QueryVo(43,48);
        List<User> userList = iUserDao.findRange(queryVo);
        System.out.println(userList);
    }

    //测试複雜標籤的sql
    @Test
    public void findByUser(){
      User user = new User();
      //user.setUsername("TheShy");
      user.setAddress("北京");
      System.out.println(iUserDao.findByUser(user));
    }

   /*
   * Just do it
   * Desinger:TheShy
   *試驗foreach標籤
   */
    @Test
    public void findRange2(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(45);
        list.add(46);
        list.add(48);
        QueryVo queryVo = new QueryVo(43,48);
        queryVo.setMang(list);
        List<User> userList = iUserDao.findRange2(queryVo);
        System.out.println(userList);
    }

    /*
     * Just do it
     * Desinger:TheShy
     *試驗foreach標籤
     */
    @Test
    public void findRange3(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(45);
        list.add(46);
        list.add(48);
        /*
        * Just do it
        * Desinger:TheShy
        *在傳入list時，可以使用list和collection做名字
        */
        List<User> userList = iUserDao.findRange3(list);
        System.out.println(userList);
    }

    @Test
    public void findRange4(){
        Integer[] integers = {45,46,48};
        List<User> userList = iUserDao.findRange4(integers);
        System.out.println(userList);
    }
}
