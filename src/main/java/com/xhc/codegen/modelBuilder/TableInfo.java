package com.xhc.codegen.modelBuilder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @title: TableInfo.java
 * @description: TODO
 * @author: 徐洪晨
 * @date: 2019年5月2日 下午1:52:59
 */
public class TableInfo {
    private String name; // 原始表名
    private String modelName;
    private String simpleName;
    private List<TableField> fields = new ArrayList<TableField>();
    private List<String> pkgs = new ArrayList<String>(); // 所有字段类型对应的Java包，import到java文件
    private TableField primaryKey; // 主键字段
    private String remarks; // 表注释

    public String getName() {
        return name;
    }


    public List<TableField> getFields() {
        return fields;
    }

    public void setFields(List<TableField> fields) {
        this.fields = fields;
        for(TableField field : fields) {
        	String pkg = field.getColumnType().getPkg();
            if (StringUtils.isEmpty(pkg) || pkgs.contains(pkg)) {
                continue;
            }
            
            pkgs.add(pkg);
        }
    }

    public List<String> getPkgs() {
        return pkgs;
    }

    public void setPkgs(List<String> pkgs) {
        this.pkgs = pkgs;
    }

    public TableField getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(TableField primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


	public String getModelName() {
		return modelName;
	}


	public void setModelName(String modelName) {
		this.modelName = modelName;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSimpleName() {
		return simpleName;
	}


	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	
}