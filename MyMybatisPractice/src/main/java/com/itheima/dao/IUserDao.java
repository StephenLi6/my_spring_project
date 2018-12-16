package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 *
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    int saveUser(User user);

    void update(User user);

    void delete(Integer id);

    User findById(Integer i);

    List<User> findByName(String s);

    Integer findTotal();

    List<User> findRange(QueryVo queryVo);

    List<User> findByUser(User user);

    List<User> findRange2(QueryVo queryVo);

    //List<User> findRange3(@Param("fuckYou") List<Integer> list);
    List<User> findRange3( List<Integer> list);

    List<User> findRange4(@Param("fucktheworld") Integer[] integers);
}
