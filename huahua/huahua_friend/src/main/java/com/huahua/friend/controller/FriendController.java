package com.huahua.friend.controller;

import com.huahua.friend.service.FriendService;
import huahua.common.Result;
import huahua.common.StatusCode;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
@CrossOrigin
public class FriendController {

    @Autowired
    FriendService friendService;
    @Autowired
    HttpServletRequest httpServletRequest;

    /**
     * 添加或者取消关注
     * @param friendid
     * @param type
     * @return
     */
    @RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
    public Result addFriend(@PathVariable("friendid") String friendid,@PathVariable("type") String type){
        //判断值是否存在
        Claims claims = (Claims) httpServletRequest.getAttribute("user_claims");
        if(null == claims){
            return new Result(false, StatusCode.AUTOROLES,"无权访问");
        }
        //判断type类型 关注 取消关注

        //关注
        if(StringUtils.equals("1",type)){
            if(friendService.addFriend(claims.getId(),friendid)==0){
                return new Result(false,StatusCode.Error,"已关注此用户哦！");
            }
        }else{//取消关注
            friendService.notLikeFriend(claims.getId(),friendid);
        }
        //返回状态
        return new Result(true,StatusCode.OK,"操作成功");
    }

    /**
     * 拉黑功能
     */
    @DeleteMapping("/{friendid}")
    public Result delete(@PathVariable("friendid") String friendid){
        Claims claims = (Claims) httpServletRequest.getAttribute("user_claims");
        if(null == claims){
            return new Result(false, StatusCode.AUTOROLES,"无权访问");
        }
        friendService.delete(claims.getId(),friendid);
        return new Result(true,StatusCode.OK,"操作成功");
    }
}
