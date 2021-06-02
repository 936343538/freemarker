package bf.data.entity.entity.coop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${className}Entity extends BaseCoopEntity {

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

}
