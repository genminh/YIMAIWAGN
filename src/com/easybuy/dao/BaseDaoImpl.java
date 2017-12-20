package com.easybuy.dao;

import com.easybuy.utils.EmptyUtils;

import java.sql.*;

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
            if (!EmptyUtils.isEmpty(params)){
                for(int i = 0; i < params.length;i++){
                    preparedStatement.setObject(i+1,params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  resultSet;
    }

    /**
     * 新增记录
     * @param sql
     * @param params
     * @return
     */
    public int executeInsert(String sql,Object[] params){
        //获取插入的id
        Long id = 0L;
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length;i ++){
                preparedStatement.setObject(i + 1,params[i]);

            }
            preparedStatement.executeUpdate();
            //获取到主键值
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                //第一个值为1
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            id = null;
        }
        return id.intValue();
    }

    /**
     * 修改和删除和新增
     * @param sql
     * @param params
     * @return 操作所影响的行数
     */
    public int exxcuteUpdate(String sql,Object[] params){
        int updateRows = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0;i<params.length;i++){
                preparedStatement.setObject(i+1,params[i]);
            }
            updateRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            updateRows = -1;
        }
        return updateRows;

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
