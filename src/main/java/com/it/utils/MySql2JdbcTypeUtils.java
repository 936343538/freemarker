package com.it.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author hongzf
 * @date 2019-10-09
 */
public class MySql2JdbcTypeUtils {

    public static Map<String, String> jdbcTypeMap = new HashMap<>();

    static {
        try {
            //加载 jdbcType.properties 文件
            Properties properties = new Properties();
            InputStream resourceAsStream = MySql2JdbcTypeUtils.class.getClassLoader().getResourceAsStream("jdbcType.properties");
            properties.load(resourceAsStream);
            jdbcTypeMap.putAll((Map)properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MySql2JdbcTypeUtils.jdbcTypeMap.forEach((k,v)-> System.out.println(k+"="+v));
    }
}
