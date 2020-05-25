package ${package}.service.impl;

import ${package}.entity.${className}Entity;
import ${package}.entity.mapper.${className}EntityMapper;
import ${package}.mapper.${className}Mapper;
import ${package}.service.${className}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@Service
public class ${className}ServiceImpl
        extends BaseServiceImpl<${className}EntityMapper, ${className}Mapper, ${className}Entity>
        implements ${className}Service {

    @Override
    public PageInfo<${className}Entity> list(${className}Entity entity, int pageNum, int pageSize) {
            PageHelper.startPage(pageNum, pageSize);
            Page<${className}Entity> page = baseMapper.list(entity);
            return page.toPageInfo();
    }
}
