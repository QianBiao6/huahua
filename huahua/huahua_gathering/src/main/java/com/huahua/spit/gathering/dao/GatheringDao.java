package com.huahua.spit.gathering.dao;

import com.huahua.spit.gathering.pojo.Gathering;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface GatheringDao extends JpaRepository<Gathering,String>,JpaSpecificationExecutor<Gathering>{
	Gathering findOneById(String id);

	/**
	 * 根据城市 查询城市下的活动
	 * @param city
	 * @param pageable
	 * @return
	 */
	Page<Gathering> findAllByCity(String city, Pageable pageable);
}
