package ${package}.${className?uncap_first}.web.request;

import ${package}.${className?uncap_first}.service.${className}QueryParam;
import com.hsit.euler.base.esb.request.AbstractRopPageRequest;
import com.hsit.tld.eb.common.DateUtil;
import com.ridge.util.BeanCopier;
import com.ridge.util.BeanCopierUtils;

import java.util.Date;

/**
 * ${date}
 * @author ${author}
 * ${table.comment!}
 */
public class ${className}ListGetRequest extends AbstractRopPageRequest {
    <#list table.columns as column>
    //${column.columnComment}<#if column.columnKey="1">[主键]</#if>
    private ${column.columnType} ${column.columnName2};
    </#list>

    <#list table.columns as column>

    public ${column.columnType} get${column.columnName2?cap_first}() {
    return this.${column.columnName2};
    }
    <#if column.columnType!="Date">
    public void set${column.columnName2?cap_first}(${column.columnType} ${column.columnName2}) {
    this.${column.columnName2} = ${column.columnName2}<#if column.columnType="String">== null ? null : ${column.columnName2}.trim()</#if>;
    <#else >
    public void set${column.columnName2?cap_first}(String ${column.columnName2}) {
    this.${column.columnName2} = DateUtil.toDate(${column.columnName2});
    </#if>
    }
    </#list>

    public ${className}QueryParam getQueryParam() {
        ${className}QueryParam queryParam = new ${className}QueryParam();
        BeanCopier copier = BeanCopierUtils.getBeanCopierInstance(${className}ListGetRequest.class, ${className}QueryParam.class);
        copier.copy(this, queryParam, null);
        return queryParam;
    }

    public ${className} to${className}() {
        ${className?cap_first} ${className!} = new ${className}();
        BeanCopier copier = BeanCopierUtils.getBeanCopierInstance(${className}ListGetRequest.class, ${className}.class);
        copier.copy(this, ${className!}, null);
        return ${className!};
    }
}

