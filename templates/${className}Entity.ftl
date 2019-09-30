package ${package}.entity;

/**
 * @author ${author}
 * @date
 */
public class ${className}Entity extends BaseEntity {

    private static final long serialVersionUID = -1L;

    <#list table.columns as column>
    //${column.columnComment}<#if column.columnKey="1">[主键]</#if>
    private ${column.columnType} ${column.columnName2};
    </#list>

}