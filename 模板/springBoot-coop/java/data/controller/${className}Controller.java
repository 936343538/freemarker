package ${package}.controller;

import bf.data.entity.entity.coop.${className}Entity;
import ${package}.resultcode.Result;
import ${package}.enums.result.StatusEnum;
import ${package}.service.${className}Service;
import ${package}.version.ApiVersion;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/{version}/${className}")
@DefaultProperties(defaultFallback = "defaultFallback")
public class ${className}Controller extends BaseController<${className}Service, ${className}Entity> {

    @ApiVersion(V1)
    @HystrixCommand
    @RequestMapping(value = "/value")
    public Result<Object> value(${className}Entity entity) {
        return new Result<>(StatusEnum.SUCCESS);
    }

}
