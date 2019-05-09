package com.huahua.spit.article.service;

import com.huahua.spit.article.dao.CommentMongoDBDao;
import com.huahua.spit.article.pojo.CommentMongoDB;
import huahua.common.utils.IdWorker;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CommentMongoDBService {
    @Autowired
    private CommentMongoDBDao commentMongoDBDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 添加评论的功能
     */
    public void add(CommentMongoDB commentMongoDB) {
        commentMongoDB.set_id(idWorker.nextId() + "");
        commentMongoDBDao.save(commentMongoDB);
    }

    public List<CommentMongoDB> queryByArticleId(String articleId) {
        return commentMongoDBDao.findAllByArticleidOrderByPublishdateDesc(articleId);
    }

    public void delete(String ids) {
        if(StringUtils.isNotEmpty(ids)){
            String[] split = ids.split(",");
            for (String s : split){
                commentMongoDBDao.deleteById(s);
            }
        }
    }
}
