package com.huahua.spit.base.service;

import com.huahua.spit.base.dao.BaseDao;
import com.huahua.spit.base.pojo.Label;
import huahua.common.utils.IdWorker;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BaseService {
    @Autowired
    private BaseDao baseDao;


    @Autowired
    private IdWorker idWorker;

    public void add(Label label) {
        // String.valueOf(idWorker.nextId());
        if(StringUtils.isNotEmpty(label.getId())){
            baseDao.save(label);
        }else{
            label.setId(idWorker.nextId()+"");
            baseDao.save(label);
        }
    }

    public List<Label> findAll() {
        return baseDao.findAll();
    }



    public List<Label> toplist(String recommend){

        return baseDao.findAllByRecommendOrderByFansDesc(recommend);
    }


    public List<Label> queryByStateAllList(String state){

        return baseDao.findAllByStateOrderByFansDesc(state);
    }

    public Label queryById(String id) {

        return  baseDao.queryById(id);
    }

    public void delete(String id) {
        baseDao.deleteById(id);
    }

    /**
     * 条件构造器
     * @param label
     * @param page
     * @param size
     * @return
     */
    public Page<Label> findSearch(Label label,Integer page,Integer size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Specification<Label> specification = this.createSpecification(label);
        return baseDao.findAll(specification,pageRequest);
    }

    private Specification<Label> createSpecification(Label label){
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                //创建sql语句
                if(null==label){
                    return null;
                }
                if(StringUtils.isNotEmpty(label.getId())){
                    //如果id为0
                    predicateList.add(cb.equal(root.get("id").as(String.class),label.getId()));
                }
                if(StringUtils.isNotEmpty(label.getLabelname())){
                    predicateList.add(cb.like(root.get("labelname").as(String.class),label.getLabelname()));
                }
                if(StringUtils.isNotEmpty(label.getState())){
                    predicateList.add(cb.equal(root.get("state").as(String.class),label.getState()));
                }
                if(null!=predicateList && predicateList.size()>0){
                    //集合转成数组 数组长度为集合长度
                    return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
                }
               return null;
            }
        };
    }
}
