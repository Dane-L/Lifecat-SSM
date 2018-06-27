package com.spring.util;

import com.spring.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class SqlSessionFactoryUtils {

    /**
     * �����
     */
    private final static Class<SqlSessionFactoryUtils> LOCK = SqlSessionFactoryUtils.class;

    private static SqlSessionFactory sqlSessionFactory = null;

    private SqlSessionFactoryUtils() {
    }

    /**
     * SqlSessionFactory -- ����ģʽ
     */
    private static SqlSessionFactory getSqlSessionFactory() {
        synchronized (LOCK) {
            if (sqlSessionFactory != null) {
                return sqlSessionFactory;
            }
            String resource = "mybatis-config.xml";
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return sqlSessionFactory;
        }
    }

    /**
     * ��һ��Session�Ự
     */
    public static SqlSession openSqlSession() {
        if (sqlSessionFactory == null) {
            getSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }

    /**
     * ���Ӳ���
     */
    public static void main(String[] args) {
        SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
        assert factory != null;

        SqlSession session = SqlSessionFactoryUtils.openSqlSession();
        assert session != null;

        UserMapper userMapper = session.getMapper(UserMapper.class);
        try {
            long count = userMapper.isUserExistedByName("test");
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}