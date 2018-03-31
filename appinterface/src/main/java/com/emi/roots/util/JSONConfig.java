package com.emi.roots.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import java.util.Collection;

/**
 * Created by yuronghao on 2017/8/14.
 */
public class JSONConfig {
    private static JsonConfig config ;
    public static JsonConfig getConfig(){
        config = new JsonConfig();
        config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        config.registerDefaultValueProcessor(Collection.class, new DefaultValueProcessor() {
            @SuppressWarnings("rawtypes")
            @Override
            public Object getDefaultValue(Class arg0) {
                return "";
            }
        });

        return config;
    }
}
