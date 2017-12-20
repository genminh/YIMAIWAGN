package com.easybuy.service.news;

import com.easybuy.dao.news.NewsDao;
import com.easybuy.dao.news.NewsDaoImpl;
import com.easybuy.entity.News;
import com.easybuy.utils.DataSourceUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    private Connection connection;
    private NewsDao newsDao;

    @Override
    public List<News> queryAllNews() throws Exception {
        List<News> newsList = new ArrayList<>();
        try {
            connection= DataSourceUtil.opeanConnection();
            newsDao = new NewsDaoImpl(DataSourceUtil.opeanConnection());
            newsList=newsDao.queryAllNews();
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            DataSourceUtil.closeConnection(connection);
        }
        return newsList;
    }
}
