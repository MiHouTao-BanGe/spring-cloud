package com.tb.springcloud.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    public LogGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog", "cacheLog","name");//和下面Config类配合接收过滤器传进来的参数
    }

    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain) -> {
            if (config.consoleLog) {
                System.out.println("console日志已开启...");
            }

            if (config.cacheLog) {
                System.out.println("cache日志已开启...");
            }

            return chain.filter(exchange);
        });
    }

    @Data
    public static class Config{
        private boolean consoleLog;
        private boolean cacheLog;
        private String name;
    }
}

