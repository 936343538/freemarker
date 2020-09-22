package ${package}.entity.mapper;

import bf.data.entity.entity.coop.${className}Entity;
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

}