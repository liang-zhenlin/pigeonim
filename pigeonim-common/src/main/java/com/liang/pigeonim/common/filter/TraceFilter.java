package com.liang.pigeonim.common.filter;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/6/15 5:14 PM
 */
public class TraceFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        MDC.put("uri", req.getRequestURI());
        resp.addHeader("traceId", TraceContext.traceId());

        try {
            filterChain.doFilter(req, servletResponse);
        } finally {
            MDC.remove("uri");
        }
    }
}
