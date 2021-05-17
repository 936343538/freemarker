package ${package}.mapper;

import ${package}.entity.${className}Entity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@Repository
public interface ${className}Mapper extends BaseMapper<${className}Entity> {

    /**
     * 逻辑删除
     * @param idList
     */
    void deleteBatch(@Param("idList") List<String> idList);

}
