package ${package}.entity.mapper;

import ${package}.entity.${className}Entity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@Repository
public interface ${className}EntityMapper extends BaseEntityMapper<${className}Entity>{

    /**
     * 批量更新
     *
     * @param list
     */
    void batchUpdateByListSelect(@Param("list") List<${className}Entity> list);

}