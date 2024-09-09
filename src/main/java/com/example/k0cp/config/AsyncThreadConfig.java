package com.example.k0cp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


@EnableAsync
@Configuration
public class AsyncThreadConfig implements AsyncConfigurer {
    private final Logger logger = LoggerFactory.getLogger(AsyncThreadConfig.class);

    @Override
    public Executor getAsyncExecutor() {
        ThreadFactory threadFactory = Thread.ofVirtual().name("virtual-executor-", 1)
                .uncaughtExceptionHandler((thread, throwable) -> {})
                .factory();
        TaskExecutorAdapter adapter = new TaskExecutorAdapter(Executors.newThreadPerTaskExecutor(threadFactory));
        /* 추후 Thread Local 에 접근이 필요 할 경우 TaskDecorator 적용 */
//        adapter.setTaskDecorator(new TaskDecorator());
        return adapter;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                logger.info("Async Task Exception Message - {}", ex.getMessage());
                logger.info("Async Task Method name -  - {}", method.getName());
                for (Object param : params) {
                    logger.info("Async Task Parameter value -  - {}", param);
                }
            }
        };
    }

//    @Bean
//    public DelegatingSecurityContextAsyncTaskExecutor taskExecutor() {
//        /** Security Context 가 ThreadLocal 로 저장 되기 때문에
//         * @Async 에서 Security Context 에 접근 하기 위한 설정
//         * */
//        return new DelegatingSecurityContextAsyncTaskExecutor((AsyncTaskExecutor) getAsyncExecutor());
//    }

    @Bean
    ExecutorService virtualThreadExecutor() {
        ThreadFactory threadFactory = Thread.ofVirtual().name("virtual-thread-task-executor-", 1)
                .uncaughtExceptionHandler((thread, throwable) -> {})
                .factory();
        return Executors.newThreadPerTaskExecutor(threadFactory);
    }
}
