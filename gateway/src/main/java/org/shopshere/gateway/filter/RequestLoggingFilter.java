package org.shopshere.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class RequestLoggingFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {


        String path = exchange.getRequest()
                .getPath()
                .value();

        String id = exchange.getRequest().getHeaders().getFirst("X-Correlation-ID");

        log.info("Incoming request path={} , corelation_id={}", path, id);


        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return 0;
    }
}