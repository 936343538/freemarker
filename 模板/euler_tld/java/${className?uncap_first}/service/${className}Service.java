package ${package}.${className?uncap_first}.service;

import ${package}.${className?uncap_first}.domain.${className};
import com.ridge.dao.Page;
import com.ridge.dao.Paging;

import java.util.List;

/**
 * ${date}
 * @author ${author}
 * ${table.comment!}
 */
public interface ${className}Service {

    Page<${className}> query(${className}QueryParam queryParam, Paging paging);

    ${className} get(String id);

    void deleteBatch(List<String> idList);
    
    void save(${className} ${className!});
    
    void update(${className} ${className!});

}