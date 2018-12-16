package com.theshy.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2317:15
 * com.theshy.utilsMyDailyProject
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*
    * Just do it
    * Desinger:TheShy
    *獲取當前線程上的鏈接
    */
    public Connection getThreadConnection(){
        Connection conn = null;
        try {
            conn = tl.get();
            if (conn == null){
                conn = dataSource.getConnection();
                tl.set(conn);
            }
             return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
    * Just do it
    * Desinger:TheShy
    *把線程和鏈接解綁
    */
    public void removeConnection(){
        tl.remove();
    }
}
