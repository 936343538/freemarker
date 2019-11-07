package com.it.freemarker;

import com.it.utils.PropertiesUtils;
import com.it.utils.StringUtils;
import com.it.zhifa.db.Column;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author hongzf
 * @date 2019-09-27
 */
public class M1 {

    private Connection conn;

    @Before
    public void init() throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
        Properties props = new Properties();
        //设置连接属性,使得可获取到表的REMARK(备注)
        props.put("remarksReporting", "true");
        props.put("user", "root");
        props.put("password", "rootroot");
        props.setProperty("remarks", "true"); //设置可以获取remarks信息
        props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
        conn = java.sql.DriverManager.
                getConnection("jdbc:mysql://localhost:3306/euler_tld?characterEncoding=utf8&useUnicode=true&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false", props);

    }

    /**
     * 获取数据库的基本信息
     *
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        //获取数据库元数据
        DatabaseMetaData dbMetaData = conn.getMetaData();
        //获取数据库产品名称
        String productName = dbMetaData.getDatabaseProductName();
        System.out.println("获取数据库产品名称" + productName);
        //获取数据库版本号
        String productVersion = dbMetaData.getDatabaseProductVersion();
        System.out.println("获取数据库版本号" + productVersion);
        //获取数据库用户名
        String userName = dbMetaData.getUserName();
        System.out.println("获取数据库用户名" + userName);
        //获取数据库连接URL
        String userUrl = dbMetaData.getURL();
        System.out.println("获取数据库连接URL" + userUrl);
        //获取数据库驱动
        String driverName = dbMetaData.getDriverName();
        System.out.println("获取数据库驱动" + driverName);
        //获取数据库驱动版本号
        String driverVersion = dbMetaData.getDriverVersion();
        System.out.println("获取数据库驱动版本号" + driverVersion);
        //查看数据库是否允许读操作
        boolean isReadOnly = dbMetaData.isReadOnly();
        System.out.println("查看数据库是否允许读操作" + isReadOnly);
        //查看数据库是否支持事务操作
        boolean supportsTransactions = dbMetaData.supportsTransactions();
        System.out.println("查看数据库是否支持事务操作" + supportsTransactions);
    }

    /**
     * 获取数据库表名
     *
     * @throws Exception
     */
    @Test
    public void testFindAllCatalogs() throws Exception {
        //获取元数据
        DatabaseMetaData metaData = conn.getMetaData();
        //获取数据库列表
        ResultSet rs = metaData.getCatalogs();
        //遍历获取所有数据库表
        while (rs.next()) {
            //打印数据库名称
            System.out.println(rs.getString(1));
        }
        //释放资源
        rs.close();
        conn.close();
    }

    @Test
    public void testFindAllTable() throws Exception {
        //获取元数据
        DatabaseMetaData metaData = conn.getMetaData();
        //获取所有的数据库表信息
        /**
         * MySQL
         *      catalog 当前操作的数据库
         *      schemaPattern null
         *      tableNamePattern {null:所有表, "":目标表}
         *      types {TABLE:表,VIEW:视图}
         * Oracle
         *      catalog 当前操作的数据库 xxx:1521:orcl
         *      schemaPattern 用户名[大写]
         *      tableNamePattern {null:所有表, "":目标表}
         *      types {TABLE:表,VIEW:视图}
         */
        ResultSet tablers = metaData.getTables("euler_tld", null, "bf_task", new String[]{"TABLE"});
        //拼装table
        while (tablers.next()) {
//            //所属数据库
//            System.out.println(tablers.getString(1));
//            //所属schema
//            System.out.println(tablers.getString(2));
//            //表名
//            System.out.println(tablers.getString(3));
//            //数据库表类型
//            System.out.println(tablers.getString(4));
//            //数据库表备注
            System.out.println(tablers.getString("REMARKS"));
        }
//        ResultSet primaryKeys = metaData.getPrimaryKeys("auler_tld", null, "tb_address");//主键
//        StringBuilder keys = new StringBuilder();
//        while (primaryKeys.next()) {
//            String keyName = primaryKeys.getString("COLUMN_NAME");
//            keys.append(keyName).append(",");
//        }
//        System.out.println(keys);
        /**
         * catalog: 如果 null ,数据库字段查出来会重复,所以这个需要指定数据库 猜想:未指定,扫描所有数据库,发现相同的表重复生成
         */
        ResultSet columns = metaData.getColumns("euler_tld", null, "bf_task", null);
//        ArrayList<Column> columnsList = new ArrayList<>();
        while (columns.next()) {
            Column column = new Column();
            String columnName = columns.getString("COLUMN_NAME");//列名
            column.setColumnName(columnName);
            String column_size = columns.getString("COLUMN_SIZE");//字段长度
//            System.out.println(column_size);
//            System.out.println("========");
            column.setColumnName2(StringUtils.toJavaVariableName(columnName));//属性名
            String typeName = columns.getString("TYPE_NAME");//数据库类型
            column.setColumnDbType(typeName);
            column.setColumnType(PropertiesUtils.customMap.get(typeName));
            String columnsRemarks = columns.getString("REMARKS");
            column.setColumnComment(StringUtils.isBlank(columnsRemarks) ? columnName : columnsRemarks);
//            //如果该列是主键
//            String pri = null;
//            if (StringUtils.contains("BF_USERINFO_ID", keys.toString().split(","))) {
//                pri = "PRI";
//            }
//            System.out.println(pri);
//            column.setColumnKey(pri);
//            columnsList.add(column);
        }
        columns.close();
//        primaryKeys.close();
//        tablers.close();
        conn.close();
    }

    @Test
    public void test() throws Exception {
        String sql = "select * from bs_user where id=? AND username = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "1063705482939731968");
        //获取ParameterMetaData对象
        ParameterMetaData paramMetaData = pstmt.getParameterMetaData();
        //获取参数个数
        int paramCount = paramMetaData.getParameterCount();
        System.out.println(paramCount);
    }

    @Test
    public void test1() throws Exception {
        String sql = "select * from bf_school_collect LIMIT 1";
        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setString(1, "1");
        //执行sql语句
        ResultSet rs = pstmt.executeQuery();
        //获取ResultSetMetaData对象
        ResultSetMetaData metaData = rs.getMetaData();
        //获取查询字段数量
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            //获取表名称
            String columnName = metaData.getColumnName(i);
            //获取java类型
            String columnClassName = metaData.getColumnClassName(i);
            //获取sql类型
            String columnTypeName = metaData.getColumnTypeName(i);
            System.out.println(columnName);
            System.out.println(columnClassName);
            System.out.println(columnTypeName);
        }
        System.out.println(metaData.getColumnCount());
    }
}
