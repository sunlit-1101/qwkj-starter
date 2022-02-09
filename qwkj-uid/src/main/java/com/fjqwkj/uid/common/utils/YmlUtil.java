package com.fjqwkj.uid.common.utils;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * yml配置文件解析工具
 * @version 1.0
 * @author: WangZ
 * @date: 2020/11/25 23:59
 * @description: yml配置文件解析工具
 */
public class YmlUtil{

    private static final Properties PROPERTIES;

    static {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            yaml.setResources(resourcePatternResolver.getResources("classpath:/*.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PROPERTIES = yaml.getObject();
    }

    public static Properties getProperties(){
        return PROPERTIES;
    }

    public static String getString(String key){
        return PROPERTIES.getProperty(key);
    }
}
