package com.easybuy.utils;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DataSourceUtil {
    public static String driver;
    private static String url;
    private static String user;
    private static String password;
    static {
        //获取配置文件的属性
        Properties params = new Properties();
        //System.out.println("Sssssss");
        //InputStream is = DataSourceUtil.class.getClassLoader().getResourceAsStream("database.properties");
        InputStream is = null;
        is = DataSourceUtil.class.getClassLoader().getResourceAsStream("database.properties");
       // System.out.println(is);
        try {
            params.load(is);//加载输入流
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver=params.getProperty("driver");
        url = params.getProperty("url");
        user = params.getProperty("username");
        password = params.getProperty("password");

    }
    //获取链接
    public static Connection opeanConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        //获取数据库的驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (connection == null || connection.isClosed()){

                connection = DriverManager.getConnection(url,user,password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }
    //关闭链接
    public static void closeConnection(ResultSet resultSet,PreparedStatement preparedStatement,Connection connection){
        try {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
