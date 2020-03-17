package com.ht.service.impl;

import com.ht.entity.Userinfo;
import com.ht.dao.UserinfoDao;
import com.ht.service.UserinfoService;
import com.ht.util.ResultMessage;
import com.ht.util.ResultToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(Userinfo)表服务实现类
 *
 * @author makejava
 * @since 2020-03-11 16:39:50
 */
@Service("userinfoService")
public class UserinfoServiceImpl implements UserinfoService {
    @Resource
    private UserinfoDao userinfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Userinfo queryById(Integer id) {
        return this.userinfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Userinfo> queryAllByLimit(int offset, int limit) {
        return this.userinfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userinfo 实例对象
     * @return 实例对象
     */
    @Override
    public Userinfo insert(Userinfo userinfo) {
        this.userinfoDao.insert(userinfo);
        return userinfo;
    }

    /**
     * 修改数据
     *
     * @param userinfo 实例对象
     * @return 实例对象
     */
    @Override
    public Userinfo update(Userinfo userinfo) {
        this.userinfoDao.update(userinfo);
        return this.queryById(userinfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userinfoDao.deleteById(id) > 0;
    }

    @Override
    public ResultToken login(Userinfo user) {
        ResultToken result = null;
        Userinfo login = userinfoDao.login(user.getUsername());
        if (login == null || login.equals("")) {
            result = new ResultToken(new ResultMessage(500,"该用户不存在"));
            // result = new ResultMessage(500, "该用户不存在");
            return result;
        } else {
            if (login.getPassword().equals(user.getPassword())) {
                System.out.println("登录成功!");
                result = new ResultToken(ResultToken.makeToken(),new ResultMessage(200,"验证登录成功"));
                // result = new ResultMessage(200, "验证登录成功");
                return result;
            } else {
                System.out.println("登录失败，请检查账号或密码...");
                result = new ResultToken(new ResultMessage(406,"验证密码失败"));
                // result = new ResultMessage(406, "验证密码失败");
                return result;
            }
        }
    }
}
