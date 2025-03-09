package com.lijs.fizz.gateway.handler;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijs.fizz.common.base.enums.ErrorCodeEnum;
import com.lijs.fizz.common.base.utils.ResultUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * 网关异常通用处理器，优先级低于 {@link ResponseStatusExceptionHandler} 执行
 *
 * @author ljs
 * @date 2025-02-06
 * @description
 */
@Slf4j
@Order(-1)
@RequiredArgsConstructor
public class GatewayGlobalExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        // 请求头设置
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (ex instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                log.debug("Gateway error : {} {}", exchange.getRequest().getPath(), ex.getMessage());
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(ResultUtils.error(ErrorCodeEnum.SYSTEM_ERROR, ex.getMessage())));
            } catch (JsonProcessingException e) {
                log.error("Gateway error response ", ex);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }

}

