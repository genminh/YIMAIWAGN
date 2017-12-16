package com.easybuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDaoImpl {
    //封装一些公共的处理方法
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public BaseDaoImpl(Connection connection){
        this.connection = connection;
    }
    //查询
    public ResultSet executeQuery(String sql,Object[] params){
         resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i < params.length;i++){
                preparedStatement.setObject(i+1,params[i]);
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  resultSet;
    }
    public boolean closeResource(){
        if (null != preparedStatement){
            try {
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public boolean closeResource(ResultSet resultSet){
        if (null != resultSet){
            try {
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
