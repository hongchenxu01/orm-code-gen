package ${options.imns};

import ${options.pons}.${model.modelName};
import ${options.mpns}.${model.modelName}Mapper;
import ${options.itns}.I${model.modelName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ${model.modelName}ServiceImpl implements I${model.modelName}Service {

    @Autowired
    ${model.modelName}Mapper ${model.name}Mapper;

    @Override
    public int deleteByPrimaryKey(${model.primaryKey.columnType.javaType} ${model.primaryKey.name}) {
        return ${model.name}Mapper.deleteByPrimaryKey(${model.primaryKey.name});
    }

    @Override
    public int insert(${model.modelName} record) {
        return ${model.name}Mapper.insert(record);
    }

    @Override
    public int insertSelective(${model.modelName} record) {
        return ${model.name}Mapper.insertSelective(record);
    }

    @Override
    public List<${model.modelName}> selectAll() {
        return ${model.name}Mapper.selectAll();
    }

    @Override
    public ${model.modelName} selectByPrimaryKey(${model.primaryKey.columnType.javaType} ${model.primaryKey.name}) {
        return ${model.name}Mapper.selectByPrimaryKey(${model.primaryKey.name});
    }

    @Override
    public int updateByPrimaryKeySelective(${model.modelName} record) {
        return ${model.name}Mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(${model.modelName} record) {
        return ${model.name}Mapper.updateByPrimaryKey(record);
    }
}