package com.roger.zuul.route;

import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RefreshRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator{
    public RefreshRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
    }

    private volatile List<Entity> entities = new ArrayList<Entity>(){{add(new Entity("/baidu", "http://www.baidu.com"));}};

    private volatile List<Route> routes = mappingEntity();

    @Override
    public List<Route> getRoutes() {
        return routes;
    }


    @Override
    public Route getMatchingRoute(final String path) {
        return routes.stream().filter(route -> route.getPath().startsWith(path)).findFirst().get();

    }

    @Override
    public void refresh() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.routes = mappingEntity();
    }
    public void addEntity(Entity entity){
        this.entities.add(entity);
    }

    private List<Route> mappingEntity(){
        return entities.stream().map(entity->{
            return new Route(entity.path, entity.path, entity.url, "", false, null);
        }).collect(Collectors.toList());
    }

    public static class Entity{
        public Entity(String path, String url){
            this.path = path;
            this.url = url;
        }
        public String path;
        public String url;
    }
}
