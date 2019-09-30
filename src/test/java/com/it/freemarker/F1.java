package com.it.freemarker;

import com.it.zhifa.db.Table;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hongzf
 * @date 2019-09-27
 */
public class F1 {
    @Test
    public void test01() throws Exception {
        //1.创建freeMarker配置实例
        Configuration cfg = new Configuration();
        //2.设置模板加载器：开始加载模板，并且把模板加载在缓存中
        cfg.setTemplateLoader(new FileTemplateLoader(new File("templates")));
        //3.创建数据模型
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("username", "张三");
        //4.获取模板
        Template template = cfg.getTemplate("2.ftl");
        List<String> list = new ArrayList<String>();
        list.add("星期一");
        list.add("星期二");
        list.add("星期三");
//        list.add("星期四");
        list.add("星期五");
        list.add("星期六");
        list.add("星期日");
        dataModel.put("list",list);
        //
        /**
         * 5.处理模板内容(i.输出到文件)
         * process:
         * 参数一：数据模型（map集合）
         * 参数二：Writer对象（文件，控制台）
         */
        //i.输出到文件
//        template.process(dataModel, new FileWriter(new File("/Users/zhifa/Downloads/F1.txt")));
        //i.打印到控制台
        template.process(dataModel, new PrintWriter(System.out));//在控制台输出内容
    }
}
