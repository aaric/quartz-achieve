package com.github.aaric.quartz.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**
 * 重写了jobFactory中创建job实例的方法，引入spring注解，通过spring注解注入spring bean
 *
 * @author Aaric, created on 2020-10-14T09:11.
 * @version 0.2.0-SNAPSHOT
 */
@Component
public class CustomJobFactory extends SpringBeanJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
