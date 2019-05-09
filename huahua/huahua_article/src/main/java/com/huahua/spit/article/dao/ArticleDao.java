package com.huahua.spit.article.dao;

import com.huahua.spit.article.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Query(nativeQuery = true,value = "select * from tb_article where id = ?")
    Article findOneById(String id);

    @Modifying
    @Query(value = "update tb_article set thumbup = thumbup + 1 where id = ?",nativeQuery = true)
    void updateArticleThumbup(String id);

    @Modifying
    @Query(value = "update tb_article set state = '1' where id = ?",nativeQuery =  true)
    void updateArticleStateByArticleId(String articleId);

    /**
     * 通过频道id 查询所属文章
     * @param channel
     * @return
     */
    public Page<Article> findAllByChannelidOrderByCreatetimeDesc(String channel, Pageable pageable);

    /**
     * 通过专栏id 查询所属文章
     * @param columnid
     * @return
     */
    public Page<Article> findAllByColumnidOrderByCreatetime(String columnid, Pageable pageable);

    /**
     * 头条列表
     * @return
     */
    @Query(value = "select * from tb_article where istop = 1 ORDER BY thumbup desc",nativeQuery = true)
    public List<Article> findAllByIstop();
}
