package com.works.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final DiscoveryClient discoveryClient;

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        System.out.println("SERVER UP");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();
        String token = req.getHeader("token");
        if ( !url.equals("/loginError") ) {
            if (token == null || token.isEmpty()) {
                res.sendRedirect("/loginError");
            } else {
                List<ServiceInstance> ls = discoveryClient.getInstances("login");
                ServiceInstance instance = ls.get(0);
                String gotoLoginUrl = instance.getUri().toString();
                gotoLoginUrl = gotoLoginUrl + "/customer/status";
                System.out.println( gotoLoginUrl );
                chain.doFilter(req, res);
            }
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
        System.out.println("SERVER DOWN");
    }

}
