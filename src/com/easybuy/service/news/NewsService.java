package com.easybuy.service.news;

import com.easybuy.entity.News;

import java.util.List;

public interface  NewsService {
    /**
     * 查询首页的新闻资讯
     * @return
     * @throws Exception
     */
    public List<News> queryAllNews() throws Exception;
}
