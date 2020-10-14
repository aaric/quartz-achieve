package com.github.aaric.quartz.config;

import com.github.aaric.quartz.quartz.CustomJobFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * Quartz配置
 *
 * @author Aaric, created on 2020-10-13T13:58.
 * @version 0.2.0-SNAPSHOT
 */
@Configuration
public class QuartzConfig {

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CustomJobFactory customJobFactory) throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        schedulerFactoryBean.setJobFactory(customJobFactory);
        return schedulerFactoryBean;
    }

//    @Bean
//    public QuartzInitializerListener quartzInitializerListener() {
//        return new QuartzInitializerListener();
//    }
//
//    @Bean
//    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws IOException {
//        return schedulerFactoryBean.getScheduler();
//    }
}
