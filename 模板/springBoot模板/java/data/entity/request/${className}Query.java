package ${package}.entity.request;

import ${package}.entity.${className}Entity;
import org.apache.commons.lang.StringUtils;
/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
public class ${className}Query extends ${className}Entity {

    public ${className}Entity insertBuild() {
        return this;
    }

    public ${className}Entity updateBuild() {
        return this;
    }

    public List<String> deleteInit() {
        String ${id} = super.get${id?cap_first}();
        if (StringUtils.isBlank(${id})) {
            return null;
        }
        String[] ${id}List = ${id}.split(",");
        List<String> list = new ArrayList<>(${id}List.length);
        Collections.addAll(list, ${id}List);
        return list;
    }
}
