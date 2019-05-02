package com.xhc.codegen;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.xhc.codegen.config.Config;
import com.xhc.codegen.config.TemplateConfig;
import com.xhc.codegen.modelBuilder.ModelHolder;
import com.xhc.codegen.modelBuilder.MySqlModelBuilder;
import com.xhc.codegen.modelBuilder.TableInfo;
import com.xhc.codegen.util.ConfigReader;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;


public class GeneratorManager {

    private String configPath;
    private String templatePath;
    private Configuration cfg;

    public GeneratorManager() {
        this("config.json", "templates");
    }

    public GeneratorManager(String configPath) {
        this(configPath, "templates");
    }

    public GeneratorManager(String configPath, String templatePath) {
        this.configPath = StringUtils.isEmpty(configPath) ? "config.json" : configPath;
        this.templatePath = StringUtils.isEmpty(templatePath) ? "templates" : templatePath;
    }

    public void Start() throws Exception {

        Config config = ConfigReader.getConfigByFilePath(Config.class, configPath);

        List<TemplateConfig> templates = config.getTemplates();
        for (TemplateConfig template : templates) {
        	MySqlModelBuilder ds = new MySqlModelBuilder();
            List<TableInfo> models = ds.buildDataModels(config.getDatasource());

            cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(new File(templatePath));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            for (TableInfo eachModel : models) {
                ProcessTemplate(eachModel, template);
            }
        }
    }

    private void ProcessTemplate(TableInfo dataModel, TemplateConfig template) throws Exception {

        ModelHolder modelHolder = new ModelHolder(dataModel, template.getOptions());
        String tempFilename = template.getTemplateFilename();
        String outputPath = template.getOutputPath();
        String outputFilenameRule = template.getOutputFilenameRule();

        String filename;

        String filenameValue;
        if (dataModel instanceof HashMap) {
            filenameValue = (String) ((HashMap) dataModel).get(propName);
        } else {
            filenameValue = (String) FieldUtils.readField(dataModel, propName, true);
        }

        filename = outputFilenameRule.replace("{" + propName + "}", filenameValue);

        outputPath = outputPath.endsWith("/") ? outputPath : outputPath + "/";

        filename = outputPath + filename;

        File outPath = new File(outputPath);
        outPath.mkdirs();

        Template temp = cfg.getTemplate(tempFilename);
        FileWriter writer = new FileWriter(new File(filename));
        temp.process(modelHolder, writer);
        writer.close();
    }
}