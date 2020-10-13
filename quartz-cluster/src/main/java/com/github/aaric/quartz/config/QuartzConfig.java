package com.github.aaric.quartz.config;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
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

    @Autowired
    private DataSource dataSource;

    @Autowired
    private QuartzJobFactory quartzJobFactory;

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource("quartz.properties"));
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJobFactory(quartzJobFactory);
        factoryBean.setQuartzProperties(quartzProperties());
        factoryBean.setOverwriteExistingJobs(true);
        factoryBean.setStartupDelay(10);
        return factoryBean;
    }

    @Bean
    public QuartzInitializerListener quartzInitializerListener() {
        return new QuartzInitializerListener();
    }

    @Bean
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

    @Component
    public static class QuartzJobFactory extends AdaptableJobFactory {

        @Autowired
        private AutowireCapableBeanFactory beanFactory;

        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            Object jobInstance = super.createJobInstance(bundle);
            beanFactory.autowireBean(bundle);
            return jobInstance;
        }
    }
}
