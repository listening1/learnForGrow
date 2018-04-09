package com.listen.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        String resource = "mapper/mybatis-config.xml";
        InputStream inputStream;
        SqlSession sqlSession = null;
        try{
            //利用mybatis自己的工具加载配置文件
            inputStream = Resources.getResourceAsStream(resource);
            //根据配置文件生成sqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //sqlSession获取
            sqlSession = sqlSessionFactory.openSession();

            //执行查询请求
            List<Map> list = sqlSession.selectList("com.listen.mybatis.StudentMapper.xml");
            //输出查询结果
            list.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
