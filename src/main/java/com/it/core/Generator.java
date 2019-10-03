package com.it.core;

import com.it.utils.FileUtils;
import com.it.utils.PropertiesUtils;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author hongzf
 * @date 2019-09-29
 */
public class Generator {
    //模板所在路径
    private String templatePath;
    //代码生成路径
    private String outPath;

    private Configuration configuration;

    public Generator(String templatePath, String outPath) throws Exception {
        this.templatePath = templatePath;
        this.outPath = PropertiesUtils.customMap.get("outPath");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        PropertiesUtils.customMap.put("date",simpleDateFormat.format(new Date()));
        configuration = new Configuration();
        //指定模板加载器
        configuration.setTemplateLoader(new FileTemplateLoader(new File(templatePath)));
    }

    //扫描所有模板并进行代码生成
    public void scanTemplatesAndProcess(Map dataMap) throws Exception {
        //加载文件夹下的所有模板文件
        List<File> srcFiles = FileUtils.searchAllFile(new File(templatePath));
        //针对每一个模板文件进行代码生成
        for (File srcFile : srcFiles) {
            executeGenerate(dataMap, srcFile);
        }
    }

    /**
     * @param dataMap 数据模型
     * @param srcFile 模板文件
     */
    private void executeGenerate(Map dataMap, File srcFile) throws Exception {
        //文件路径处理
        String templateFileName = srcFile.getAbsolutePath().replace(templatePath, "");
        String outFileName = processTemplateString(templateFileName, dataMap);
        //读取模板
        Template template = configuration.getTemplate(templateFileName);
        template.setOutputEncoding("utf-8");//指定生成字节码的编码文件
        //创建文件
        File mkdir = FileUtils.mkdir(outPath, outFileName);
        //模板处理
        FileWriter fileWriter = new FileWriter(mkdir);
        template.process(dataMap,fileWriter);
        fileWriter.close();
    }

    private String processTemplateString(String templateString, Map dataModel) throws Exception {
        StringWriter out = new StringWriter();
        Template template = new Template("ts", new StringReader(templateString), configuration);
        template.process(dataModel, out);
        return out.toString();
    }

    public static void main(String[] args) throws Exception {
        String templatePath = "/Users/zhifa/IdeaProjects/freemarker/模板/springBoot 模板";
        String outPath = "/Users/zhifa/Downloads";
        Generator generator = new Generator(templatePath,outPath);
        Map map = new HashMap();
        map.put("name","java");
        generator.scanTemplatesAndProcess(map);
    }
}
