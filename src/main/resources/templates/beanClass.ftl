package ${options.pons};

<#list model.pkgs as pkg>
import ${pkg};
</#list>
import lombok.Data;

/**
* ${model.remarks}表
*/
@Data
public class ${model.modelName} {

    <#list model.fields as field>
    private ${field.columnType.javaType} ${field.humpName}; // ${field.remarks}
    </#list>

}