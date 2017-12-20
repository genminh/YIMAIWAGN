package com.easybuy.dao.news;

import com.easybuy.entity.News;

import java.util.List;

public interface NewsDao {
    /**
     * 查询首页的新闻资讯
     * @return
     * @throws Exception
     */
    public List<News> queryAllNews() throws Exception;
}
