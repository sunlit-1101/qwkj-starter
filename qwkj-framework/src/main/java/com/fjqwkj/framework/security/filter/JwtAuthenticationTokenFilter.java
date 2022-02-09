package com.fjqwkj.framework.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fjqwkj.common.utils.ip.IpUtils;
import com.fjqwkj.common.utils.uid.SnowFlake;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fjqwkj.common.core.domain.model.LoginUser;
import com.fjqwkj.common.utils.SecurityUtils;
import com.fjqwkj.common.utils.StringUtils;
import com.fjqwkj.framework.web.service.TokenService;

/**
 * token过滤器 验证token有效性
 * 
 * @author ruoyi
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private TokenService tokenService;

    private SnowFlake snowFlake = new SnowFlake();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        ThreadContext.put("requestId",String.valueOf(snowFlake.nextId()));
        ThreadContext.put("uri",request.getRequestURI());
        ThreadContext.put("ip", IpUtils.getIpAddr(request));
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
        {
            if(null != loginUser.getUser()){
                ThreadContext.put("userId",String.valueOf(loginUser.getUser().getUserId()));
                ThreadContext.put("userName",loginUser.getUser().getUserName());
            }
            tokenService.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
