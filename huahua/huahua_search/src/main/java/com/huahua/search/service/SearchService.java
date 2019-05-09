package com.huahua.search.service;

import com.huahua.search.dao.SearchDao;
import com.huahua.search.pojo.ArticleEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    private SearchDao searchDao;

    public Page<ArticleEs> searchArticle(String keywords, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return searchDao.findAllByTitleLikeOrContentLike(keywords,keywords,pageRequest);
    }

    public void add(ArticleEs articleEs) {
        searchDao.save(articleEs);
    }

    public void delete(ArticleEs articleEs) {
        searchDao.updateStateById(articleEs);
    }
}
