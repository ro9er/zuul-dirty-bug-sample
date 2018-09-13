package com.roger.zuul.controller;

import com.netflix.discovery.converters.Auto;
import com.roger.zuul.route.RefreshRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ApplicationEventPublisherAware{

    private ApplicationEventPublisher publisher = null;

    @Autowired
    private RefreshRouteLocator simpleRouteLocator;

    @GetMapping("/refreshRoute")
    public String refresh(){
        simpleRouteLocator.addEntity(new RefreshRouteLocator.Entity("/163", "http://www.163.com"));
        publisher.publishEvent(new RoutesRefreshedEvent(simpleRouteLocator));
        return "yes";
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
