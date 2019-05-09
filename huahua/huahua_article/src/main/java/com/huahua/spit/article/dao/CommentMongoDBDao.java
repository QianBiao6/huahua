package com.huahua.spit.article.dao;

import com.huahua.spit.article.pojo.CommentMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 评论dao
 */
public interface CommentMongoDBDao extends MongoRepository<CommentMongoDB,String> {

   List<CommentMongoDB> findAllByArticleidOrderByPublishdateDesc(String articleid);
}
