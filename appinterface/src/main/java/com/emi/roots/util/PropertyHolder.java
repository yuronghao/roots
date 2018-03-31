/**
 * 2015-3-11 
 * PropertyHolder.java 
 * author:赵永飞
 */
package com.emi.roots.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.util.Locale;
import java.util.Properties;

/**
 * @author zhaoyf
 */

public class PropertyHolder {
	 public  static final Logger LOG = LoggerFactory.getLogger(PropertyHolder.class);
	private static final Properties props = new Properties();

    static {
        init();
    }

    public static Properties getProperties() {
        return props;
    }
  
    private static void init() {
            String systemConfig="/config.properties";
            /*String localConfig="/config.local.properties";
            String dbConfig="/cn/gov/nteport/nplat/db.properties";
            String localDBConfig="/db.local.properties";*/
            ClassPathResource cr = null;
            try{
                cr = new ClassPathResource(systemConfig);
                props.load(cr.getInputStream());
                LOG.info("装入主配置文件:"+systemConfig);
            }catch(Exception e){
                LOG.info("装入主配置文件"+systemConfig+"失败!", e);
            }
        
            LOG.info("系统配置属性装载完毕");
            LOG.info("******************属性列表***************************");
            for(String propertyName : props.stringPropertyNames()){
                LOG.info("  "+propertyName+" = "+props.getProperty(propertyName));
            }
            LOG.info("***********************************************************");
            
            //指定日志输出语言
          //  LOG.setLocale(getLogLanguage());
    }
    /**
     * 日志使用什么语言输出
     * @return 
     */
    public static Locale getLogLanguage(){
       String language = getProperty("log.locale.language");
       return Locale.forLanguageTag(language);
    }

    public static boolean getBooleanProperty(String name) {
        String value = props.getProperty(name);

        return "true".equals(value);
    }

    public static int getIntProperty(String name) {
        String value = props.getProperty(name);

        return Integer.parseInt(value);
    }

    public static String getProperty(String name) {
        String value = props.getProperty(name);

        return value;
    }

    public static void setProperty(String name, String value) {
        props.setProperty(name, value);
    }
}




/*
public class PropertyHolder {	
	static{
        String wechatConfig = "D:/work/wxportal_svn/src/com/emi/portal/wechat/base/util/wechat.config.properties";
        ClassPathResource cr = null;
        try {
        	InputStream ff=new FileInputStream(new File(wechatConfig));
        	System.out.println(ff);
        	// cr = new ClassPathResource(wechatConfig);
            PropertyHolder.getInstance().load(ff);
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
	private static Properties p = null;
	public static Properties getInstance(){
		if(p==null){
			p= new Properties();
		}
		return p;
	}
	
	public static String getProperty(String key){
		return getInstance().getProperty(key);
	}
	public static void main(String[] args){
		System.out.println(getInstance().getProperty("wechat.token"));
	}
}
*/