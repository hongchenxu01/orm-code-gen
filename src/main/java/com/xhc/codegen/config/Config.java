package com.xhc.codegen.config;

import java.util.List;

import com.xhc.codegen.modelBuilder.MySqlConfig;

/**
 * Created by bigmamonkey on 5/22/17.
 */
public class Config {

    private MySqlConfig datasource;
    
    private List<TemplateConfig> templates;
    
    
	public MySqlConfig getDatasource() {
		return datasource;
	}
	public void setDatasource(MySqlConfig datasource) {
		this.datasource = datasource;
	}
	public List<TemplateConfig> getTemplates() {
		return templates;
	}
	public void setTemplates(List<TemplateConfig> templates) {
		this.templates = templates;
	}

}
