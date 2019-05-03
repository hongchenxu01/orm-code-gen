package com.xhc.codegen.modelBuilder;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.xhc.codegen.util.StringUtil;


public class MySqlModelBuilder{

    public List<TableInfo> buildDataModels(MySqlConfig config) throws Exception {

        try {
            Class.forName(config.getDriverClassName());
        } catch (ClassNotFoundException e) {
            throw new Exception("load driver exception..", e);
        }

        List<TableInfo> tableInfos = new ArrayList<TableInfo>();
        Connection connection;
        try {
            connection = DriverManager.getConnection(config.getDbUrl(), config.getUsername(), config.getPassword());
            DatabaseMetaData dbMetaData = connection.getMetaData();
            
            Map<String, String> tableCfgMap = config.getTables();

            List<TableInfo> allTableList = getAllTableList(dbMetaData, tableCfgMap);
            
            final List<String> tableNameList = new ArrayList<String>();
            
            for(Map.Entry<String, String> tableCfg: tableCfgMap.entrySet()) {
            	tableNameList.add(tableCfg.getKey());
            }
            
            allTableList.removeIf(new Predicate<TableInfo>() {

				public boolean test(TableInfo t) {
					return !tableNameList.contains(t.getName());
				}
			});
            tableInfos.addAll(allTableList);

        } catch (SQLException e) {
            throw new Exception("load database metadata exception..", e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableInfos;
    }

    /**
     * 获得该用户下面的所有表
     */
    public static List<TableInfo> getAllTableList(DatabaseMetaData dbMetaData, Map<String, String> tableCfgMap) throws Exception {
        List<TableInfo> tableInfos = new ArrayList<TableInfo>();
        try {
            // table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
            String[] types = {"TABLE"};
            ResultSet rs = dbMetaData.getTables(null, null, "%", types);

            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");  //表名
                // String tableType = rs.getString("TABLE_TYPE");  //表类型
                String remarks = rs.getString("REMARKS");       //表备注

                List<TableField> tableFields = getTableFields(dbMetaData, tableName);
                final String primaryKeyName = getAllPrimaryKeys(dbMetaData, tableName);
                TableField primaryKey = tableFields.stream()
                        .filter(new Predicate<TableField>() {
							public boolean test(TableField t) {
								return t.getName().equals(primaryKeyName);
							}
						})
                        .findFirst().get();
                TableInfo tableInfo = new TableInfo();
                tableInfo.setName(tableName);
                tableInfo.setFields(tableFields);
                tableInfo.setPrimaryKey(primaryKey);
                tableInfo.setRemarks(remarks);
                tableInfo.setModelName(tableCfgMap.get(tableName));
                tableInfo.setSimpleName(StringUtil.ToSimpleName(tableCfgMap.get(tableName)));
                tableInfos.add(tableInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableInfos;
    }

    /**
     * 获得表或视图中的所有列信息
     */
    public static List<TableField> getTableFields(DatabaseMetaData dbMetaData, String tableName) throws Exception {

        ArrayList<TableField> tableFields = new ArrayList<TableField>();

        ResultSet rs = dbMetaData.getColumns(null, null, tableName, "%");
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");//列名
            int dataType = rs.getInt("DATA_TYPE"); //对应的    类型
            int columnSize = rs.getInt("COLUMN_SIZE");//列大小
            String remarks = rs.getString("REMARKS");//列描述
            TableField tableField = new TableField();
            tableField.setName(columnName);
            tableField.setHumpName(StringUtil.UnderlineToHump(columnName));
            tableField.setDataType(dataType);
            tableField.setColumnSize(columnSize);
            tableField.setRemarks(remarks);
            tableFields.add(tableField);
        }
        return tableFields;
    }

    /**
     * 获得一个表的主键信息
     */
    public static String getAllPrimaryKeys(DatabaseMetaData dbMetaData, String tableName) throws Exception {

        ResultSet rs = dbMetaData.getPrimaryKeys(null, null, tableName);
        if (!rs.next()) {
            throw new Exception(String.format("table:%s don't have primary key", tableName));
        }
        return rs.getString("COLUMN_NAME");//列名
    }
}