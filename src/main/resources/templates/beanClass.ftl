package ${options.pons};

<#list model.pkgs as pkg>
import ${pkg};
</#list>

/**
* ${model.remarks}表
*/
@Data
public class ${model.upperCaseName} {

    <#list model.fields as field>
    private ${field.columnType.javaType} ${field.name}; // ${field.remarks}
    </#list>

}