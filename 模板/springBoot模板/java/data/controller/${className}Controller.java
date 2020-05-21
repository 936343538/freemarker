package ${package}.controller;

import com.bf.data.service.entity.${className}Entity;
import com.bf.data.service.resultcode.BaseRetCode;
import com.bf.data.service.resultcode.Result;
import com.bf.data.service.service.${className}Service;
import com.bf.data.service.utils.CodeUtils;
import com.bf.data.service.version.ApiVersion;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@RestController
@RequestMapping(value = "/{version}/${className}/")
public class ${className}Controller extends BaseController<${className}Service, ${className}Entity> {

    @ApiVersion("1.0.0")
    @HystrixCommand(defaultFallback = "objectFallBack")
    @RequestMapping(value = "add")
    public Result<Object> add(@RequestBody ${className}Entity entity) {
        Result<Object> result = new Result<>();
        try {
            entity.set${id?cap_first}(CodeUtils.uuid32());
            result.setInfo(dService.insert(entity));
        } catch (Exception e) {
            logger.error("${className}Controller add error ", e);
            result.setCode(BaseRetCode.ERROR);
        }
        return result;
    }

    @ApiVersion("1.0.0")
    @HystrixCommand(defaultFallback = "objectFallBack")
    @RequestMapping(value = "delete")
    public Result<Object> delete(String ${id}) {
        Result<Object> result = new Result<>();
        try {
            result.setInfo(dService.delete(${id}));
        } catch (Exception e) {
            logger.error("${className}Controller add error ", e);
            result.setCode(BaseRetCode.ERROR);
        }
        return result;
    }

    @ApiVersion("1.0.0")
    @HystrixCommand(defaultFallback = "objectFallBack")
    @RequestMapping(value = "list")
    public Result<Object> list(@RequestBody ${className}Entity entity, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Result<Object> result = new Result<>();
        try {
            result.setInfo(dService.list(entity, pageNum, pageSize));
        } catch (Exception e) {
            logger.error("${className}Controller list error ", e);
            result.setCode(BaseRetCode.ERROR);
        }
        return result;
    }

}
