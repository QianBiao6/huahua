package com.huahua.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import huahua.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class ManagerFilter extends ZuulFilter {
    @Autowired
    private JwtUtil jwtUtil;

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
        RequestContext requestContext = RequestContext.getCurrentContext();
        //得到request域
        HttpServletRequest request = requestContext.getRequest();

        if(request.getMethod().equals("OPTIONS")){
            return null;
        }

        if(request.getRequestURI().indexOf("login")>0){
            return null;
        }
        //得到头部信息
        String header = request.getHeader("Authorization");
        //判断是否有头部信息
        if(header!=null && "".equals(header)){
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                try{
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String)claims.get("roles");
                    if(roles.equals("admin")){
                        //转发头部信息
                        requestContext.addZuulRequestHeader("Authorization",header);
                        return  null;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    requestContext.setSendZuulResponse(false);//终止运行
                }
            }
        }
        requestContext.setSendZuulResponse(false);//终止运行
        requestContext.setResponseStatusCode(403);
        requestContext.setResponseBody("权限不足");
        requestContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
