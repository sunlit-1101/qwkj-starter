package com.fjqwkj.uid.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.PropertyResolver;

/**
 * 配置工具
 * @version 1.0
 * @author: WangZ
 * @date: 2020/11/25 23:55
 * @description: 配置工具
 */
public class PropertyUtil extends PropertySourcesPlaceholderConfigurer {
    private static PropertyResolver propertyResolver;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     ConfigurablePropertyResolver propertyResolver) throws BeansException {
        super.processProperties(beanFactoryToProcess, propertyResolver);
        PropertyUtil.propertyResolver = propertyResolver;
    }

    public static String getString(String key) {
        return propertyResolver.getProperty(key);
    }
}
