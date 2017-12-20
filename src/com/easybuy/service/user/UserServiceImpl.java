package com.easybuy.service.user;

import com.easybuy.dao.user.UserDao;
import com.easybuy.dao.user.UserDaoImpl;
import com.easybuy.entity.User;
import com.easybuy.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private Connection connection;
    private UserDao userDao;
    @Override
    public User getUserByLoginName(String loginName) {
        //获取数据库的链接
        User user = null;
        try {
            connection = DataSourceUtil.opeanConnection();
            userDao = new UserDaoImpl(connection);
            user = userDao.getUserByLoginName(loginName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DataSourceUtil.closeConnection(connection);
        }
        return user;

    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public boolean save(User user) {
        boolean flag=false;
        Connection connection = null;
        try {
            connection = DataSourceUtil.opeanConnection();
            UserDao userDao = new UserDaoImpl(connection);
            int count=userDao.save(user);
            flag=count>0;
        } catch (SQLException e) {
            flag=false;
            e.printStackTrace();
        } finally {
            DataSourceUtil.closeConnection(connection);
            return flag;
        }
    }
}
