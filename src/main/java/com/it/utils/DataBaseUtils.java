package com.it.utils;

import com.it.zhifa.db.Column;
import com.it.zhifa.db.DataBase;
import com.it.zhifa.db.Table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author hongzf
 * @date 2019-09-27
 */
public class DataBaseUtils {

    /**
     * 获取数据库连接
     *
     * @param db
     * @return
     * @throws Exception
     */
    public static Connection getConnection(DataBase db) throws Exception {
        Properties props = new Properties();
        //设置连接属性,使得可获取到表的REMARK(备注)
        props.put("remarksReporting", "true");
        props.put("user", db.getUserName());
        props.put("password", db.getPassWord());
        //设置可以获取remarks信息
        props.put("remarks", "true");
        //设置可以获取tables remarks信息
        props.put("useInformationSchema", "true");
//        Class.forName(db.getDriver());//注册驱动
        return DriverManager.getConnection(db.getUrl(), props);
    }

    /**
     * 获取数据库列表
     *
     * @throws Exception
     */
    public static List<String> getSchemas(DataBase db) throws Exception {
        Connection connection = null;
        ResultSet rs = null;
        List<String> list;
        try {
            //获取元数据
            connection = getConnection(db);
            DatabaseMetaData metaData = connection.getMetaData();
            //获取数据库列表
            rs = metaData.getCatalogs();
            list = new ArrayList<>();
            //遍历获取所有数据库表
            while (rs.next()) {
                //打印数据库名称
                list.add(rs.getString(1));
            }
        } finally {
            //释放资源
            if (rs != null) {
                rs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    /**
     * 获取数据库中的表和字段构造实体类
     *
     * @return
     * @throws Exception
     */
    public static List<Table> getDbInfo(DataBase db) throws Exception {
        //1.获取连接
        Connection connection = getConnection(db);
        //2.获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //3.获取所有的数据库表信息  dbName：指定数据库
        String dbName = db.getDbName();
        List<Table> list = new ArrayList<>();
        try {
//            while (tables.next()) {
//                String tableName = tables.getString("TABLE_NAME");//表名称
            String tableNames = db.getTableName();//表名称
            String[] tableNameArr = tableNames.split(",");
            for (String tableName : tableNameArr) {
                Table table = new Table();
                String className = removePrefix(tableName);//类名
                ResultSet tables = metaData.getTables(dbName, null, tableName, new String[]{"TABLE"});
                while (tables.next()) {
                    table.setComment(tables.getString("REMARKS"));
                }
                ResultSet primaryKeys = metaData.getPrimaryKeys(dbName, null, tableName);//主键
                StringBuilder keys = new StringBuilder();
                while (primaryKeys.next()) {
                    String keyName = primaryKeys.getString("COLUMN_NAME");
                    keys.append(keyName).append(",");
                }
                table.setName(tableName);
                table.setName2(className);
//            String remarks = "";
                table.setKey(keys.toString());
                list.add(table);
                //处理表中的所有字段
                ResultSet columns = metaData.getColumns(dbName, null, tableName, null);
                ArrayList<Column> columnsList = new ArrayList<>();
                while (columns.next()) {
                    Column column = new Column();
                    String columnName = columns.getString("COLUMN_NAME");//列名
                    column.setColumnName(columnName);
                    column.setColumnName2(StringUtils.toJavaVariableName(columnName));//属性名
                    String typeName = columns.getString("TYPE_NAME");//数据库类型
                    typeName = getTypeName(typeName);
                    String tyName = MySql2JdbcTypeUtils.jdbcTypeMap.get(typeName);
                    column.setColumnDbType(tyName == null ? typeName : tyName);
                    column.setColumnType(PropertiesUtils.customMap.get(typeName));
                    column.setColumnSize(columns.getString("COLUMN_SIZE"));//字段长度
                    String columnsRemarks = columns.getString("REMARKS");
                    column.setColumnComment(StringUtils.isBlank(columnsRemarks) ? columnName : columnsRemarks);
                    //如果该列是主键
                    String pri = "0";
                    if (StringUtils.contains(columnName, table.getKey().split(","))) {
                        pri = "1";
                    }
                    column.setColumnKey(pri);
                    columnsList.add(column);
                }
                table.setColumns(columnsList);
                System.out.println(tableName + "  表处理成功!");
            }
//            }
        } finally {
//            tables.close();
            connection.close();
        }
        return list;
    }

    /**
     * 针对数值类型包含非空要求的判定处理
     *                     customMap.put("INT UNSIGNED","Integer");
     *                     customMap.put("DECIMAL UNSIGNED","Integer");
     * @param typeName
     * @return
     */
    private static String getTypeName(String typeName) {
        if (StringUtils.isNotBlank(typeName)) {
            int i = typeName.indexOf("UNSIGNED");
            if (i>0) {
                return typeName.substring(0, i).trim();
            }
        }
        return null;
    }

    private static String removePrefix(String tableName) {
        String prefixes = PropertiesUtils.customMap.get("tableRemovePrefixes");
        String temp = tableName;
        for (String pf : prefixes.split(",")) {
            temp = StringUtils.removePrefix(temp, pf, true);
        }
        return StringUtils.makeAllWordFirstLetterUpperCase(temp);
    }

    public static void main(String[] args) throws Exception {
        DataBase dataBase = new DataBase("MYSQL", "euler_tld", "bf_coop_employee");
        dataBase.setUserName("root");
        dataBase.setPassWord("rootroot");
        List<Table> tbAddress = DataBaseUtils.getDbInfo(dataBase);
        for (Table address : tbAddress) {
            System.out.println(address);
        }
    }
}
