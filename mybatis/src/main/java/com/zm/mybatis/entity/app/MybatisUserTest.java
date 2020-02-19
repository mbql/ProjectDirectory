package com.zm.mybatis.entity.app;

import com.zm.mybatis.entity.User;
import com.zm.mybatis.entity.mapper.UserMapper;
import com.zm.mybatis.entity.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

/**
 * @author mbql
 * @date 2020/2/19 16:42
 */
public class MybatisUserTest {
    private final static SqlSession sqlSession = MyBatisUtils.openSession();
    private static UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    public static void main(String[] args) {
        // queryUser();
        // queryUserById(3);
        // addUser();
        // updateUser();
        delUser(6);
    }

    /**
     * 查询所有用户
     */
    public static void queryUser() {
        List<User> list = mapper.list();
        for (User user : list) {
            System.out.println(user.toString());
        }
        MyBatisUtils.close(sqlSession);
    }

    /**
     * 根据用户id查询
     */
    public static User queryUserById(Integer id) {
        User user = mapper.selectOne(id);
        if (user != null) {
            System.out.println(user.toString());
            return user;
        }else {
            System.out.println("该用户没找到...");
            MyBatisUtils.close(sqlSession);
            return null;
        }
    }

    /**
     * 添加用户信息
     *
     * @return
     */
    public static int addUser() {
        //创建一个对象
        User user = new User();
        user.setUserName("gg");
        user.setPassword("123456");
        user.setAge(19);
        user.setSex(1);
        user.setBirthday(new Date());
        int count = mapper.addUser(user);
        //添加对象信息
        if (count > 0) {
            sqlSession.commit();
            System.out.println("添加成功...");
            return 200;
        }else {
            sqlSession.rollback();
            MyBatisUtils.close(sqlSession);
            System.out.println("添加失败、、、回滚成功.......");
            return -1;
        }
    }

    /**
     * 根据id更新用户信息
     *
     * @return
     */
    public static int updateUser() {
        User user = new User();
        user.setId(5);
        user.setUserName("测试修改");
        user.setPassword("abcdefg");
        user.setAge(38);
        user.setSex(1);
        user.setBirthday(new Date());
        if (queryUserById(user.getId()) != null) {
            mapper.updateUser(user);
            sqlSession.commit();
            System.out.println("修改编号为：" + user.getId() + "的用户成功！！！");
            return 200;
        }else {
            sqlSession.rollback();
            MyBatisUtils.close(sqlSession);
            System.out.println("none find User...");
            return -1;
        }
    }

    /**
     * 根据用户id删除用户信息
     * @param id
     * @return
     */
    public static int delUser(Integer id) {
        if (queryUserById(id) != null) {
            mapper.delUser(id);
            sqlSession.commit();
            System.out.println("删除该用户成功！！！");
            return 200;
        }else {
            sqlSession.rollback();
            MyBatisUtils.close(sqlSession);
            System.out.println("该用户不存在，不用进行删除......");
            return -1;
        }
    }
}
