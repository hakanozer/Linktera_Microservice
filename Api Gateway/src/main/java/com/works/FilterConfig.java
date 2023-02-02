package com.works;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
public class FilterConfig implements Filter {

    final DiscoveryClient discoveryClient;
    public FilterConfig(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        try {

            String query = req.getQueryString() != null ? "?"+req.getQueryString() : "";
            String url = req.getRequestURI();
            String serviceName = url.split("/")[1];
            System.out.println(serviceName);
            List<ServiceInstance> ls = discoveryClient.getInstances(serviceName);
            ServiceInstance instance = ls.get(0);
            String gotoServiceUrl = instance.getUri().toString();
            gotoServiceUrl = gotoServiceUrl + url + query;
            System.out.println( gotoServiceUrl );
            // req.getRequestDispatcher(gotoServiceUrl).forward(req, res);
            // res.setHeader("Location", gotoServiceUrl );
            //res.setHeader("token", "83ADB5FB604AE72458F9B02EE7A23F14");
            res.sendRedirect( gotoServiceUrl );
        }catch (Exception ex) {
            System.err.println("Api Gateway - Error : " + ex);
            //chain.doFilter(req, res);
        }


    }

}
