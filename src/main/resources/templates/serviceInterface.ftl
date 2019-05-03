package ${options.itns};

import ${options.pons}.${model.modelName};
import java.util.List;

public interface ${model.modelName}Service {

    int deleteByPrimaryKey(${model.primaryKey.columnType.javaType} ${model.primaryKey.name});

    int insert(${model.modelName} record);

    int insertSelective(${model.modelName} record);

    List<${model.modelName}> selectAll();

    ${model.modelName} selectByPrimaryKey(${model.primaryKey.columnType.javaType} ${model.primaryKey.name});

    int updateByPrimaryKeySelective(${model.modelName} record);

    int updateByPrimaryKey(${model.modelName} record);
}