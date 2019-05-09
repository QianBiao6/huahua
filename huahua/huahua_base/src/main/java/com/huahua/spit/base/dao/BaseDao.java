package com.huahua.spit.base.dao;

import com.huahua.spit.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 标签数据访问接口
 */
public interface BaseDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {

    /**
     * 推荐标签列表
     * @param recommend
     * @return
     */
    List<Label> findAllByRecommendOrderByFansDesc(String recommend);

    /**
     * 有效标签列表
     * @param state
     * @return
     */
    List<Label> findAllByStateOrderByFansDesc(String state);


    //@Query 查询语句 nativeQuery 执行 sql语句  "?"单个传参 如果多个参数 已"?1" 开始 依次累加
    @Query(nativeQuery = true,value = "select * from tb_label where id = ? ")
    Label queryById(String id);
}
