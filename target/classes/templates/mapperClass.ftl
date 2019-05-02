package ${options.mpns};

import ${options.pons}.${model.modelName};
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface ${model.modelName}Mapper {

    int deleteByPrimaryKey(${model.primaryKey.columnType.javaType} ${model.primaryKey.name});

    int insert(${model.modelName} record);

    int insertSelective(${model.modelName} record);

    List<${model.modelName}> selectAll();

    ${model.modelName} selectByPrimaryKey(${model.primaryKey.columnType.javaType} ${model.primaryKey.name});

    int updateByPrimaryKeySelective(${model.modelName} record);

    int updateByPrimaryKey(${model.modelName} record);
}