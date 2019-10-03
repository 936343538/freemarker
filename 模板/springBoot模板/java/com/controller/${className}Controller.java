package ${package}.controller;

import com.bf.data.service.entity.${className}Entity;
import com.bf.data.service.resultcode.BaseRetCode;
import com.bf.data.service.resultcode.Result;
import com.bf.data.service.service.${className}Service;
import com.bf.data.service.version.ApiVersion;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/{version}/${className}/")
public class ${className}Controller extends BaseController<${className}Service, ${className}Entity> {

    @ApiVersion("1.0.0")
    @HystrixCommand(defaultFallback = "objectFallBack")
    @RequestMapping(value = "method")
    public Result<Object> method() {
        Result<Object> result = new Result<>();
        try {
            result.setInfo(dService.get(""));
        } catch (Exception e) {
            logger.error("${className}Controller method error ", e);
            result.setCode(BaseRetCode.ERROR);
        }
        return result;
    }

}
