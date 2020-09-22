package com.it.core;

import com.it.utils.FileUtils;
import com.it.utils.PropertiesUtils;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 模板+数据=文件
 *
 * @author hongzf
 * @date 2019-09-29
 */
public class Generator {
    /**
     * 模板所在路径
     */
    private String templatePath;
    /**
     * 代码生成路径
     */
    private String outPath;

    private Configuration configuration;

    public Generator(String templatePath, String outPath) throws Exception {
        this.templatePath = templatePath;
        this.outPath = PropertiesUtils.customMap.get("outPath");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        PropertiesUtils.customMap.put("date", simpleDateFormat.format(new Date()));
        configuration = new Configuration();
        //指定模板加载器
        configuration.setTemplateLoader(new FileTemplateLoader(new File(templatePath)));
    }

    /**
     * 扫描所有模板并进行代码生成
     *
     * @param dataMap
     * @throws Exception
     */
    public void scanTemplatesAndProcess(Map dataMap) throws Exception {
        //加载文件夹下的所有模板文件
        List<File> srcFiles = FileUtils.searchAllFile(new File(templatePath));
        //针对每一个模板文件进行代码生成
        for (File srcFile : srcFiles) {
            executeGenerate(dataMap, srcFile);
        }
    }

    /**
     * 通过模板文件进行代码生成
     *
     * @param dataMap 数据模型
     * @param srcFile 模板文件
     */
    private void executeGenerate(Map dataMap, File srcFile) throws Exception {
        //文件路径处理
        String templateFileName = srcFile.getAbsolutePath().replace(templatePath, "");
        String outFileName = processTemplateString(templateFileName, dataMap);
        //读取模板
        Template template = configuration.getTemplate(templateFileName);
        //指定生成字节码的编码文件
        template.setOutputEncoding("utf-8");
        //创建文件
        File mkdir = FileUtils.mkdir(outPath, outFileName);
        //模板处理
        FileWriter fileWriter = new FileWriter(mkdir);
        template.process(dataMap, fileWriter);
        fileWriter.close();
    }

    /**
     * 通过模板名获取文件名
     *
     * @param templateString
     * @param dataModel
     * @return
     * @throws Exception
     */
    private String processTemplateString(String templateString, Map dataModel) throws Exception {
        StringWriter out = new StringWriter();
        Template template = new Template("ts", new StringReader(templateString), configuration);
        template.process(dataModel, out);
        return out.toString();
    }
}
