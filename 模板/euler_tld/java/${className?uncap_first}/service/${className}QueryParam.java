package ${package}.${className?uncap_first}.service;

import com.hsit.euler.base.criteria.Criteria;
import ${package}.util.CommonUtil;

/**
 * ${date}
 * @author ${author}
 * ${table.comment!}
 */
public class ${className}QueryParam {
    <#list table.columns as column>
    /**
     * ${column.columnComment}<#if column.columnKey="1">[主键]</#if>
     */
    private ${column.columnType} ${column.columnName2};
    </#list>

    <#list table.columns as column>
    public ${column.columnType} get${column.columnName2?cap_first}() {
        return this.${column.columnName2};
    }

    public void set${column.columnName2?cap_first}(${column.columnType} ${column.columnName2}) {
        this.${column.columnName2} = ${column.columnName2}<#if column.columnType="String">== null ? null : ${column.columnName2}.trim()</#if>;
    }
    </#list>

    public Criteria getCriteria() {
        return Criteria.build()
        <#list table.columns as column>
                <#if column.columnName="DATA_STATE">
                <#break>
                </#if>
                <#if column.columnKey="0">
                .equal("${column.columnName}", ${column.columnName2})
                </#if>
        </#list>
                .equal("DATA_STATE", CommonUtil.DATE_STATE.NO);
    }
}

