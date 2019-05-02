package com.xhc.codegen.modelBuilder;

import java.util.Map;

/**
 * @title: MySqlConfig.java
 * @description: TODO
 * @author: 徐洪晨
 * @date: 2019年5月2日 下午1:39:46
 */
public class MySqlConfig {

    private String dbUrl; // 数据库访问链接
    private String driverClassName; //驱动类名称
    private String username;
    private String password;
    private Map<String, String> tables; // 可指定要生成的表名

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public Map<String, String> getTables() {
		return tables;
	}

	public void setTables(Map<String, String> tables) {
		this.tables = tables;
	}
}
