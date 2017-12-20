package com.easybuy.dao.news;

import com.easybuy.dao.BaseDaoImpl;
import com.easybuy.entity.News;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl extends BaseDaoImpl implements NewsDao {
    public NewsDaoImpl(Connection connection) {
        super(connection);
    }
    public News tableToClass(ResultSet resultSet) throws SQLException {
        News news = new News();
        news.setId(resultSet.getInt("id"));
        news.setTitle(resultSet.getString("title"));
        news.setContent(resultSet.getString("content"));
        news.setCreateTime(resultSet.getDate("createTime"));
        return news;
    }
    @Override
    public List<News> queryAllNews() throws Exception {
        List<News> newsList = new ArrayList<>();
        StringBuffer sql =new StringBuffer(" select id,title,content,createTime FROM easybuy_news ") ;
        sql.append(" limit 0, 5");
        ResultSet resultSet = this.executeQuery(sql.toString(), null);
        try {
            while (resultSet.next()) {
                News news = this.tableToClass(resultSet);
                newsList.add(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            this.closeResource(resultSet);
            this.closeResource();
        }
        return newsList;
    }
}
