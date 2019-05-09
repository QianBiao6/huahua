package com.huahua.friend.dao;

import com.huahua.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String>, JpaSpecificationExecutor<Friend> {

    /**
     * 更新为相互喜欢
     */
    @Modifying
    @Query(nativeQuery = true,value = "update tb_friend set islike = ?3 where userid = ?1 and friendid = ?2")
    public void updateLike(String userid,String friendid,String islike);

    /**
     * 验证当前登录用户是否关注了此用户
     */
    @Query(nativeQuery = true,value = "select count(1) from tb_friend where friendid = ?2 and userid = ?1")
    public int selectByUserCount(String userid,String friendid);

    /**
     * 登录用户
     * @param userid
     * @param friendid
     */
    public void deleteByUseridAndFriendid(String userid,String friendid);
}
