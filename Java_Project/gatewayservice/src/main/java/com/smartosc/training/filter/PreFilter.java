package com.smartosc.training.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 31/07/2020 - 10:22 AM
 * @created_by Hieupv
 * @since 31/07/2020
 */
public class PreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        System.out.println("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
        return null;
    }
}
