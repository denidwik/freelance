package com.project.freelance.config;

import com.project.freelance.gateway.impl.UserJPAGatewayImpl;
import com.project.freelance.repository.UserRepository;
import com.project.freelance.security.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.listener.RetryListenerSupport;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableRetry
@Slf4j
public class ServiceConfiguration {

    @Bean
    public List<RetryListener> retryListeners() {
        return Collections.singletonList(new RetryListenerSupport() {

            @Override
            public <T, E extends Throwable> void onError(
                    RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
                log.warn("Retryable method {} threw {} exception {}",
                        context.getAttribute("context.name"),
                        context.getRetryCount(), throwable);
            }
        });
    }

    /* GATEWAY CONFIGURATION */
    @Bean
    public UserJPAGatewayImpl userJPAGateway(UserRepository userRepository) {
        return new UserJPAGatewayImpl(userRepository);
    }


}
