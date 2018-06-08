package com.common.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.PropertiesUtil;

/**
 *
 * @author zhaomy07
 * @date 2017年5月27日 下午3:40:31
 * @version V1.0.0 description：
 * 跨域Filter 
 */
public class CORSFilter implements Filter {

    // 跨域白名单设置  通过cros.properties文件进行加载
    private final static String ORIGIN_LIMIT = PropertiesUtil.loadProperties("cros")
    		.getProperty("ALLOW.ORIGIN.LIMIT");
    static Collection<String> ORIGIN_LIST = null;
    static{
        String[] originLimits = ORIGIN_LIMIT.split(",");
        ORIGIN_LIST = new HashSet<String>(Arrays.asList(originLimits));
    }
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // 跨域白名单过滤
        String origin = httpRequest.getHeader("Origin");
        if (ORIGIN_LIST.contains(origin)) {
            httpResponse.addHeader("Access-Control-Allow-Origin", origin);
        }
        // 指定浏览器CORS请求会额外发送的头信息字段
        httpResponse.addHeader("Access-Control-Allow-Headers","Origin, x-requested-with, Authorization, Content-Type, Accept, X-Cookie");
        // 表示是否允许发送Cookie true为允许（CORS请求默认不发送Cookie和HTTP认证信息）
        httpResponse.addHeader("Access-Control-Allow-Credentials", "true"); 
        // 用来列出浏览器的CORS请求会用到哪些HTTP方法
        httpResponse.addHeader("Access-Control-Allow-Methods","GET,POST,PUT,OPTIONS,DELETE");
        
        // 非简单请求的CORS请求，会在正式通信之前，增加一次HTTP查询请求，称为"预检"请求（preflight）
        if (httpRequest.getMethod().equals("OPTIONS")) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        chain.doFilter(request, response);
        
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

}
