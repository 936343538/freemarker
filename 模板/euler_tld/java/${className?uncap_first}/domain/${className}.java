package ${package}.${className?uncap_first}.domain;

import com.hsit.euler.base.domain.BaseDomain;

import java.util.Date;
/**
 * ${date}
 * @author ${author}
 * ${table.comment!}
 */
public class ${className} extends BaseDomain {

    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "${table.name}";

    <#list table.columns as column>
    //${column.columnComment}<#if column.columnKey="1">[主键]</#if>
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
}
