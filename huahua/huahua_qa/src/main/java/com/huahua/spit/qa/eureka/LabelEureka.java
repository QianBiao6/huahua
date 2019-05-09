package com.huahua.spit.qa.eureka;

import huahua.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//调用的是哪一个微服务
@FeignClient("huahua-base")
public interface LabelEureka {

    //value要写上全路径
    @RequestMapping(method = RequestMethod.GET,value = "/label/{labelId}")
    public Result queryById(@PathVariable("labelId") String labelId);
}
