package ${package}.service;

import bf.data.entity.entity.coop.${className}Entity;
import ${package}.entity.request.${className}Query;
import com.github.pagehelper.PageInfo;


import java.util.List;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
public interface ${className}Service extends BaseService<${className}Entity> {

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param query
     * @return
     */
    PageInfo<${className}Entity> listPage(int pageNum, int pageSize, ${className}Query query);

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    PageInfo<${className}Entity> list(${className}Query query);

    /**
     * 逻辑删除
     *
     * @param idList
     */
    void deleteBatch(List<String> idList);
}
