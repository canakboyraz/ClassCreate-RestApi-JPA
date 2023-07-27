package com.works.team.configs;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class FilterConfig implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;


        // session control
        String[] freeUrl ={"/student/register","/student/login"} ;
        String url = req.getRequestURI();
        boolean loginStatus = true;
        for (String item : freeUrl)
        {
            if ( url.equals(item) ) {
                loginStatus = false;
                break;
            }
        }


        if (loginStatus) {
            boolean status = req.getSession().getAttribute("user") == null;
            if (status) {
                // session null
                PrintWriter printWriter = res.getWriter();
                printWriter.println("{ \"status\": false, \"result\": \"Please Login\" }");
                res.setContentType("application/json");
                res.setStatus(401);
            }else {
                // session true
                filterChain.doFilter(req, res);
            }
        }else {
            filterChain.doFilter(req, res);
        }
    }
}
