package com.example.k0cp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


@EnableAsync
@Configuration
public class AsyncThreadConfig implements AsyncConfigurer {

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
}
