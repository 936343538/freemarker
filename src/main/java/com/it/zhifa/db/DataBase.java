package com.it.zhifa.db;

//数据库实体类
public class DataBase {

    private static String mysqlUrl = "jdbc:mysql://[ip]:[port]/[db]?useUnicode=true&amp&characterEncoding=UTF8&serverTimezone=UTC";
    private static String oracleUrl = "jdbc:oracle:thin:@[ip]:[port]:[db]";

    private String dbType;//数据库类型
    private String driver;
    private String userName;
    private String passWord;
    private String url;
    private String dbName;//数据库名称
    private String tableName;//表名

    public DataBase() {
    }

    public DataBase(String dbType) {
        this(dbType, "127.0.0.1", "3306", "");
    }

    public DataBase(String dbType, String db) {
        this(dbType, "127.0.0.1", "3306", db);
    }

    public DataBase(String dbType, String dbName, String tableName) {
        this(dbType, "127.0.0.1", "3306", dbName);
        this.dbName = dbName;
        this.tableName = tableName;
    }

    public DataBase(String dbType, String ip, String port, String dbName) {
        this.dbType = dbType;
        if ("MYSQL".endsWith(dbType.toUpperCase())) {
            this.driver = "com.mysql.jdbc.Driver";
            this.url = mysqlUrl.replace("[ip]", ip).replace("[port]", port).replace("[db]", dbName);
        } else {
            this.driver = "oracle.jdbc.driver.OracleDriver";
            this.url = oracleUrl.replace("[ip]", ip).replace("[port]", port).replace("[db]", dbName);
        }
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static String getMysqlUrl() {
        return mysqlUrl;
    }

    public static void setMysqlUrl(String mysqlUrl) {
        DataBase.mysqlUrl = mysqlUrl;
    }

    public static String getOracleUrl() {
        return oracleUrl;
    }

    public static void setOracleUrl(String oracleUrl) {
        DataBase.oracleUrl = oracleUrl;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
