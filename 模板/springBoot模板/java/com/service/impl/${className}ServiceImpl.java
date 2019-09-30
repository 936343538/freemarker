package ${package}.service.impl;

import ${package}.entity.${className}Entity;
import ${package}.entity.mapper.${className}EntityMapper;
import ${package}.mapper.${className}Mapper;
import ${package}.service.${className}Service;
import org.springframework.stereotype.Service;


@Service
public class ${className}ServiceImpl extends BaseServiceImpl<${className}EntityMapper, ${className}Mapper, ${className}Entity>
        implements ${className}Service {

}
