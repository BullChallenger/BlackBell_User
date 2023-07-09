package com.example.blackbell_user.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                // CircuitBreaker Open 여부를 결정하는 FailureRate 현재 100번 중 4번 실패하면 circuitBreaker 동작
                // Default : 50
                .failureRateThreshold(4)
                // CircuitBreaker 를 Open 한 상태를 유지하는 지속 시간을 의미
                // Default : 60초
                .waitDurationInOpenState(Duration.ofMillis(1000))
                // CircuitBreaker 가 Close 될 때, 통신 결과 기록하는 Sliding Window 의 유형 결정
                // Default : COUNT_BASED
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                // CircuitBreaker 가 Close 될 때, 호출되는 Sliding Window 의 크기
                // Default : 100
                .slidingWindowSize(2)
                .build();

        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
                // Supplier 즉, 요청을 보낸 서비스 (Order) 가 얼마간 응답하지 않았을 시, CircuitBreaker 를 작동시킬지 여부
                // Default : 4초
                .timeoutDuration(Duration.ofSeconds(4))
                .build();

        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(timeLimiterConfig)
                .circuitBreakerConfig(circuitBreakerConfig)
                .build()
        );
    }
}
