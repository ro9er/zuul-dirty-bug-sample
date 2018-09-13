package com.roger.zuul.config;

import com.roger.zuul.route.RefreshRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableConfigurationProperties({ ZuulProperties.class })
public class ZuulConfig {

    @Autowired
    protected ZuulProperties zuulProperties;

    @Bean
    public RefreshRouteLocator routeLocator(){
        return new RefreshRouteLocator("/", zuulProperties);
    }
}
