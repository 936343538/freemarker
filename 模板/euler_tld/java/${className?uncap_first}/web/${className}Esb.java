package ${package}.${className?uncap_first}.web;

import ${package}.${className?uncap_first}.web.request.${className}ListGetRequest;
import ${package}.${className?uncap_first}.service.${className}Service;
import com.hsit.euler.base.esb.BaseEsb;
import com.hsit.euler.base.esb.request.BaseIdRequest;
import com.hsit.euler.base.esb.request.BaseIdsRequest;
import com.hsit.euler.base.esb.response.CommonResponse;
import com.rop.annotation.ServiceMethod;
import com.rop.annotation.ServiceMethodBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ${date}
 * @author ${author}
 * ${table.comment!}
 */
@ServiceMethodBean(version = "1.0")
public class ${className}Esb extends BaseEsb {

    interface Methods {
        String ${table.name?upper_case}_LIST_GET = "${className?uncap_first}.list.get";
        String ${table.name?upper_case}_GET = "${className?uncap_first}.get";
        String ${table.name?upper_case}_DELETE = "${className?uncap_first}.delete";
        String ${table.name?upper_case}_ADD = "${className?uncap_first}.add";
        String ${table.name?upper_case}_UPDATE = "${className?uncap_first}.update";
    }

    @Autowired
    private ${className}Service ${className?uncap_first}Service;

    @ServiceMethod(method = Methods.${table.name?upper_case}_LIST_GET)
    public Object get${className}List(${className}ListGetRequest request) {
        return ${className?uncap_first}Service.query(request.getQueryParam(), request.getPaging());
    }

    @ServiceMethod(method = Methods.${table.name?upper_case}_GET)
    public Object get${className}(BaseIdRequest request) {
        return ${className?uncap_first}Service.get(request.getId());
    }

    @ServiceMethod(method = Methods.${table.name?upper_case}_DELETE)
    public Object delete${className}(BaseIdsRequest request) {
        ${className?uncap_first}Service.deleteBatch(request.getIdList());
        return new CommonResponse(true);
    }

    @ServiceMethod(method = Methods.${table.name?upper_case}_ADD)
    public Object add${className}(${className}ListGetRequest request) {
        ${className?uncap_first}Service.save(request.to${className}());
        return new CommonResponse(true);
    }

    @ServiceMethod(method = Methods.${table.name?upper_case}_UPDATE)
    public Object update${className}(${className}ListGetRequest request) {
        ${className?uncap_first}Service.update(request.to${className}());
        return new CommonResponse(true);
    }
}
