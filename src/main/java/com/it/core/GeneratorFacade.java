package com.it.core;

import com.it.utils.DataBaseUtils;
import com.it.utils.PropertiesUtils;
import com.it.zhifa.Settings;
import com.it.zhifa.db.Column;
import com.it.zhifa.db.DataBase;
import com.it.zhifa.db.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hongzf
 * @date 2019-09-29
 */
public class GeneratorFacade {
    private String templatePath;
    private String outPath;
    private Settings settings;
    private DataBase db;

    private Generator generator;

    public GeneratorFacade(String templatePath, String outPath, Settings settings, DataBase db) throws Exception {
        this.templatePath = templatePath;
        this.outPath = outPath;
        this.settings = settings;
        this.db = db;
        this.generator = new Generator(templatePath, outPath);
    }

    //针对数据库表生成
    public void generatorByTable(DataBase db) throws Exception {
        //查询数据库获取所有表信息
        List<Table> tableList = DataBaseUtils.getDbInfo(db);
        for (Table table : tableList) {
            //根据数据库表信息，构造数据模型并生成代码

            Map<String, Object> dataModel = getDataModel(table);
            for (Map.Entry<String, Object> stringObjectEntry : dataModel.entrySet()) {
                System.out.println(stringObjectEntry.getKey() + "---" + stringObjectEntry.getValue());
            }
            System.out.println("______________________");
            generator.scanTemplatesAndProcess(dataModel);
        }
    }

    private Map<String, Object> getDataModel(Table table) {
        //自定义配置
        Map<String, Object> stringObjectMap = new HashMap<>(PropertiesUtils.customMap);
        //元数据
        stringObjectMap.put("table", table);
        //Settings
        stringObjectMap.putAll(this.settings.getSettingMap());
        stringObjectMap.put("className", table.getName2());
        Column column = table.getColumns().get(0);
        stringObjectMap.put("id", column.getColumnName2());//id 主键表小写 bfUserId
        stringObjectMap.put("capId",column.getColumnName());//id 主键大写 BF_USER_ID
        return stringObjectMap;
    }
}
