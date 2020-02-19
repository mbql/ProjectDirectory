package com.zm.mybatis.entity.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author mbql
 * @date 2020/2/19 17:29
 *
 * Mybatis连接工具类
 */
public class MyBatisUtils {

    private static SqlSessionFactory factory = null;
    private static ThreadLocal<SqlSession> tl = new ThreadLocal<SqlSession>();

    /**
     * 使用静态代码块优先读取mybatis配置文件
     */
    static {
        // 1 读取配置文件 config.xml
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        // 2 创建SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    /**
     * 打开SqlSession连接
     *
     * @return
     */
    public static SqlSession openSession() {
        // 3 创建SqlSession
        SqlSession sqlSession = tl.get();
        if (sqlSession == null) {
            sqlSession = factory.openSession();
            tl.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭Sqlsession连接
     *
     * @param sqlSession
     */
    public static void close(SqlSession sqlSession) {
        if (sqlSession != null) {
            tl.remove();
            sqlSession.close();
        }
    }
}
