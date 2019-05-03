package com.qiqi.member_management.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * BeanConfig类简述
 *
 * @Description 项目中的一些配置bean
 * @Author shen.chu1
 * @Date 2019年05月02号 13:57
 * @Version 1.0.0
 */
@Configuration
public class BeanConfig {

    /**
     * 线程池大小
     **/
    private int corePoolSize = 10 ;
    /**
     * 最大数
     **/
    private int maxPoolSize  = 50 ;
    /**
     * 队列容量
     **/
    private int queueCapacity = 1000 ;
    /**
     * 活跃秒
     **/
    private int keepAliveSeconds = 300 ;

    /**
     * taskExecutor(异步任务配置)
     *
     * @Param 
     * @return org.springframework.core.task.TaskExecutor
     * @exception 
     * @Date  2019-05-02 14:01:48
     **/
    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        // 拒绝策略  由调用线程处理任务
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return taskExecutor;
    }
}
