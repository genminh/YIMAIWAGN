package com.easybuy.dao.user;

import com.easybuy.entity.User;

import java.sql.SQLException;

public interface UserDao {
    /**
     * 根据用户的账号从数据库中获取用户的信息
     * @param loginName
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public User getUserByLoginName(String loginName) throws Exception;

    /**
     * 用户注册
     * @param user
     * @return
     */
    int  save(User user);
}
