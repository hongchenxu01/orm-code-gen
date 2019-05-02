package com.xhc.codegen.config;

/**
 * @title: ModelConfig.java
 * @description: TODO
 * @author: 徐洪晨
 * @date: 2019年5月2日 下午4:30:15
 */
public class ModelConfig {

    private String tableName;
    
    private String modelName;
    
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
}
