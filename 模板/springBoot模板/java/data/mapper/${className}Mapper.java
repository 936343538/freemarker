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

}