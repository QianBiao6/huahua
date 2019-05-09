package com.huahua.friend.service;

import com.huahua.friend.dao.FriendDao;
import com.huahua.friend.dao.NoFriendDao;
import com.huahua.friend.entity.Friend;
import com.huahua.friend.entity.NOFriend;
import com.huahua.friend.eureka.UserEureka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {

    @Autowired
    FriendDao friendDao;
    @Autowired
    NoFriendDao noFriendDao;
    @Autowired
    UserEureka userEureka;

    /**
     * 关注用户
     * @param userid
     * @param friendid
     * @return
     */
    public int addFriend(String userid,String friendid){
        //判断用户已经添加了这个好友，则不进行如何操作，并且返回0
        if(friendDao.selectByUserCount(userid,friendid)>0){//登录用户已关注
            return 0;
        }
        //判断没有添加 向喜欢表中添加记录
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        //判断对方是否也关注你 如果有 则将双方islike都更改为 1
        if(friendDao.selectByUserCount(friendid,userid)>0){ //参数互换 及验证被关注用户是否关注登录用户
            friendDao.updateLike(userid,friendid,"1");
            friendDao.updateLike(friendid,userid,"1");
        }
        //调用spring cloud 微服务 修改用户表的关注数 粉丝数
        //userid 修改关注数
        userEureka.incfollowCount(userid,1);
        //friend 修改粉丝数
        userEureka.incfansCount(friendid,1);
        return 1;
    }

    public void notLikeFriend(String userid, String friendid) {
        //取消关注 删除记录
        friendDao.deleteByUseridAndFriendid(userid,friendid);
        //判断是否相互关注
        //互相关注 修改islike 0
        if(friendDao.selectByUserCount(friendid,userid)>0){
            friendDao.updateLike(friendid,userid,"0");
        }
        //调用spring cloud 微服务 修改修改用户表的关注数 粉丝数
        //userid 修改关注数
        userEureka.incfollowCount(userid,-1);
        //friend 修改粉丝数
        userEureka.incfansCount(friendid,-1);

    }

    public void delete(String userid,String friendid) {
        //删除登录用户关注此用户的记录
        friendDao.deleteByUseridAndFriendid(userid,friendid);
        //互相关注的情况 将被关注用户islike改为 0
        if(friendDao.selectByUserCount(friendid,userid)>0){
            friendDao.updateLike(friendid,userid,"0");
        }
        //tb_friend表中插入一条数据
        NOFriend noFriend = new NOFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        //调用spring cloud 微服务 修改用户表的关注数，粉丝数
        //userid 修改关注数
        userEureka.incfollowCount(userid,1);
        //friend 修改粉丝数
        userEureka.incfansCount(userid,1);
    }
}
