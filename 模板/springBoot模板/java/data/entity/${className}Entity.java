package ${package}.entity;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
public class ${className}Entity extends BaseEntity {

    private static final long serialVersionUID = -1L;

    <#list table.columns as column>
        <#if column.columnName="DATA_STATE">
            <#break>
        </#if>
    /**
     * ${column.columnComment}<#if column.columnKey="1">[主键]</#if>
     */
    private ${column.columnType} ${column.columnName2};
    </#list>

    <#list table.columns as column>
        <#if column.columnName="DATA_STATE">
            <#break>
        </#if>
    public ${column.columnType} get${column.columnName2?cap_first}() {
        return this.${column.columnName2};
    }

    public void set${column.columnName2?cap_first}(${column.columnType} ${column.columnName2}) {
        this.${column.columnName2} = ${column.columnName2}<#if column.columnType="String"> == null ? null : ${column.columnName2}.trim()</#if>;
    }
    </#list>

}
