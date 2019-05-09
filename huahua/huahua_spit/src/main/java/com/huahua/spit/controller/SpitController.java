package com.huahua.spit.controller;

import com.huahua.spit.entity.Spit;
import com.huahua.spit.service.SpitService;
import huahua.common.PageResult;
import huahua.common.Result;
import huahua.common.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/spit")
@CrossOrigin
public class SpitController {

    @Autowired
    private SpitService spitService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(Spit spit){
        /*Claims claims = (Claims) httpServletRequest.getAttribute("admin_claims");
        if(null == claims){
            return new Result(false,StatusCode.AUTOROLES,"权限不足");
        }*/
        spitService.add(spit);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public Result delete(@PathVariable String id){
        spitService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 根据上级id查询吐槽的数据 分页
     */
    @RequestMapping(method = RequestMethod.GET,value = "/comment/{parentid}/{page}/{size}")
    public Result findByPidList(@PathVariable("parentid") String parentid,
                                @PathVariable("page") Integer page,
                                @PathVariable("size") Integer size){
        Page<Spit> spitList = spitService.findByPidList(parentid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(spitList.getTotalElements(),spitList.getContent()));
    }

    /**
     * 吐槽点赞
     */
    @RequestMapping(method = RequestMethod.PUT,value = "/thumbup/{spitId}")
    public Result updateThumbup(@PathVariable String spitId){
        Boolean flag = spitService.updateThumbup(spitId);
        if(flag == true){
            return new Result(true,StatusCode.OK,"点赞成功！");
        }else{
            return new Result(false,StatusCode.Error,"只能点一次攒哦！");
        }
    }

    /**
     * spit分页
     */
    @RequestMapping(method = RequestMethod.POST,value = "search/{page}/{size}")
    public Result spitPage(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        Page<Spit> spitPage = spitService.search(page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(spitPage.getTotalElements(),spitPage.getContent()));
    }
}
