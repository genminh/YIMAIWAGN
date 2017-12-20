package com.easybuy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 设置编码过滤器
 */
@WebFilter(urlPatterns = {"/*"}, filterName = "EncodeFilter",initParams = {@WebInitParam(name = "encode",value = "utf-8")})
public class EncodeFilter implements Filter {
    private String encode = null;
    public void destroy() {
        encode = null;
    }

    public void init(FilterConfig config) throws ServletException {
        String encode=config.getInitParameter("encode");
        if (this.encode == null){
            this.encode = encode;
        }
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        if (null == request.getCharacterEncoding()){
            request.setCharacterEncoding(encode);
        }
        chain.doFilter(request,response);
    }

}
