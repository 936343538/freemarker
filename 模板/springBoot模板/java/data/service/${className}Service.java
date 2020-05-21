package ${package}.service;

import ${package}.entity.${className}Entity;
import com.github.pagehelper.PageInfo;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
public interface ${className}Service extends BaseService<${className}Entity> {

    /**
     * 列表查询
     *
     * @param entity
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<${className}Entity> list(${className}Entity entity, int pageNum, int pageSize);
}
