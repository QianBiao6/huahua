package com.huahua.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

public class WebFilter extends ZuulFilter {

    /**
     * 执行的时间
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 多个过滤器的执行顺序、数字越小，越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否开启当前过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        //得到上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //得到request域
        HttpServletRequest request = currentContext.getRequest();
        //得到头部信息
        String header = request.getHeader("Authorization");
        //判断是否有头部信息
        if(header!=null && "".equals(header)){
            //把头部信息继续向下传
            currentContext.addZuulRequestHeader("Authorization",header);
        }
        return null;
    }
}