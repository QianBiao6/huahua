package com.huahua.search.dao;

import com.huahua.search.pojo.ArticleEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface SearchDao extends ElasticsearchCrudRepository<ArticleEs,String> {

    Page<ArticleEs> findAllByTitleLikeOrContentLike(String title,String content, Pageable pageable);

    @Query(value = "update articleEs set state = 0 where id = #{id}")
    void updateStateById(ArticleEs articleEs);
}
