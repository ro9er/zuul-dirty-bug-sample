package com.roger.zuul.config;

/**
 * @Author: Roger Law
 * @Date: 2018/9/12
 * @Description:
 * add http client factory bean configuration
 */

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientFactoryConfig {
    @Bean
    public ApacheHttpClientFactory apacheHttpClientFactory(){
        return new AutoDecompressionHttpClientFactory();
    }

    private static class AutoDecompressionHttpClientFactory implements ApacheHttpClientFactory{
        public AutoDecompressionHttpClientFactory() {
        }

        public HttpClientBuilder createBuilder() {
            return HttpClientBuilder.create().disableCookieManagement().useSystemProperties();
        }
    }
}
