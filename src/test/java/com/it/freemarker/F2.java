package com.it.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hongzf
 * @date 2019-09-27
 */
public class F2 {
    @Test
    public void test() throws Exception {
        Configuration cfg = new Configuration();
        String templateString = "我喜欢学习：${name?uncap_first}";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name","Java");
        /**
         * 自定义模板
         * 1.模板名称
         * 2.模板的正文内容
         * 3.configuration对象
         */
        Template template = new Template("",new StringReader(templateString),cfg);
        template.process(map, new PrintWriter(System.out));

    }
}
