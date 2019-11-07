package ${package}.${className?uncap_first}.service.impl;

import ${package}.${className?uncap_first}.dao.${className}Dao;
import ${package}.${className?uncap_first}.domain.${className};
import ${package}.${className?uncap_first}.service.${className}Service;
import ${package}.${className?uncap_first}.service.${className}QueryParam;
import com.hsit.euler.base.service.BaseService;
import com.ridge.dao.Page;
import com.ridge.dao.Paging;
import com.hsit.euler.thirdparty.mybatis.MybatisHelper2;
import com.ridge.util.CodeUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${date}
 * @author ${author}
 * ${table.comment!}
 */
@Service
public class ${className}ServiceImpl extends BaseService implements ${className}Service {

    @Autowired
    private ${className}Dao ${className?uncap_first}Dao;

    public Page<${className}> query(${className}QueryParam queryParam, Paging paging) {
        List<${className}> ${className!}List = ${className?uncap_first}Dao.queryByCriteria(queryParam.getCriteria(), MybatisHelper2.toRowBounds(paging));
        return MybatisHelper2.toPage(${className!}List, paging);
    }

    public ${className} get(String ${id}) {
        return ${className?uncap_first}Dao.selectByPrimaryKey(${id});
    }

    @Transactional
    public void deleteBatch(List<String> idList) {
        if (CollectionUtils.isNotEmpty(idList)) {
            ${className?uncap_first}Dao.deleteBatch(idList);
        }
    }

    public void save(${className} ${className?uncap_first}) {
        ${className?uncap_first}.set${id?cap_first}(CodeUtils.uuid32());
        ${className?uncap_first}Dao.insert(${className?uncap_first});
    }

    public void update(${className} ${className?uncap_first}) {
        ${className?uncap_first}Dao.updateByPrimaryKey(${className?uncap_first});
    }

}