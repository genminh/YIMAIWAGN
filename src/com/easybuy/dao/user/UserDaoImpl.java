package com.easybuy.dao.user;

import com.easybuy.dao.BaseDaoImpl;
import com.easybuy.entity.User;
import com.easybuy.utils.EmptyUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl  extends BaseDaoImpl implements UserDao{
    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    public User tableToClass(ResultSet resultSet) throws Exception{
        User user = new User();
        user.setLoginName(resultSet.getString("loginName"));
        user.setUserName(resultSet.getString("userName"));
        user.setPassword(resultSet.getString("password"));
        user.setSex(resultSet.getInt("sex"));
        user.setIdentityCode(resultSet.getString("identityCode"));
        user.setEmail(resultSet.getString("email"));
        user.setMobile(resultSet.getString("mobile"));
        user.setType(resultSet.getInt("type"));
        return user;
    }

    @Override
    public User getUserByLoginName(String loginName) throws Exception {
        StringBuffer sql = new StringBuffer("select * from easyBuy_user where 1=1");
        //判断参数
        List<Object> params = new ArrayList<>();
        //判断是否为空
        if (!EmptyUtils.isEmpty(loginName)){
            //如果不为空
            sql.append(" and  loginName = ?");
            params.add(loginName);
        }
        ResultSet resultSet = executeQuery(sql.toString(),params.toArray());
        User user = null;
        while (resultSet.next()){
            user = tableToClass(resultSet);
        }
        return user;
    }

    @Override
    public int save(User user) {
        Integer id  = 0;
        try {
            String sql=" INSERT into easybuy_user(loginName,userName,password,sex,identityCode,email,mobile) values(?,?,?,?,?,?,?) ";
            Object param[]=new Object[]{user.getLoginName(),user.getUserName(),user.getPassword(),user.getSex(),user.getIdentityCode(),user.getEmail(),user.getMobile()};
            id=this.executeInsert(sql,param);
            user.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            this.closeResource();
        }
        return id;
    }
}
