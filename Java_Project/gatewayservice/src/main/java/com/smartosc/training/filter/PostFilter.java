package com.smartosc.training.filter;

import com.netflix.zuul.ZuulFilter;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 31/07/2020 - 10:23 AM
 * @created_by Hieupv
 * @since 31/07/2020
 */
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
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
        System.out.println("Inside Response Filter");

        return null;
    }
}
