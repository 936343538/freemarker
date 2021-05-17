package ${package}.${className?uncap_first}.dao;

import ${package}.${className?uncap_first}.domain.${className};
import com.hsit.euler.base.criteria.Criteria;
import com.hsit.euler.base.dao.GenericMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${date}
 * @author ${author}
 * ${table.comment!}
 */
@Repository
public interface ${className}Dao extends GenericMapper<${className}, String>{


    List<${className}> queryByCriteria(@Param("criteria") Criteria criteria, RowBounds rowBounds);

    int insertSelective(${className} ${className?uncap_first});

    void deleteBatch(@Param("idList") List<String> idList);

    /**
     * 逻辑删除,用来替换 deleteBatch
     * @param idList
     */
    void updateBatch(@Param("idList") List<String> idList);
}
