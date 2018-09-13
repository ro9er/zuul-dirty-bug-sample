package com.roger.zuul.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class RouteRefreshListener implements ApplicationListener<ApplicationEvent>, Ordered {

    @Autowired
    private RouteLocator routeLocator;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        if(applicationEvent instanceof RoutesRefreshedEvent){
            if(routeLocator instanceof RefreshableRouteLocator){
                ((RefreshableRouteLocator) routeLocator).refresh();
            }
        }
        
    }

    @Override
    public int getOrder() {
        //be the first to be execute
        return -100;
    }
}
