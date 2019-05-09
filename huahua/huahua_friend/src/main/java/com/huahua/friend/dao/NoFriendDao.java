package com.huahua.friend.dao;

import com.huahua.friend.entity.NOFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NoFriendDao extends JpaRepository<NOFriend,String>, JpaSpecificationExecutor<NOFriend> {
}
