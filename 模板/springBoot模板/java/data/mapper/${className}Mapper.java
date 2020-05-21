package ${package}.mapper;

import ${package}.entity.${className}Entity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;
import com.github.pagehelper.Page;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@Repository
public interface ${className}Mapper extends BaseMapper<${className}Entity> {

        /**
         * 列表查询
         *
         * @param entity 实体类
         * @return
         */
        Page<${className}Entity> list(@Param("entity") ${className}Entity entity);
}