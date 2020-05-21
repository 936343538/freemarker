package bf.app.service.bfdata;

import bf.app.service.config.BfGatewayConfig;
import bf.data.entity.entity.${className}Entity;
import bf.data.entity.resultcode.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@FeignClient(name = "${r"${bf.gateway.name}"}", configuration = BfGatewayConfig.class)
public interface I${className}Service {

    @RequestMapping(value = "/bf/v1.0.0/${className}/add", method = RequestMethod.POST)
    Result<Object> add(@RequestBody ${className}Entity entity);

    @RequestMapping(value = "/bf/v1.0.0/${className}/get", method = RequestMethod.POST)
    Result<Object> get(@RequestParam("entityId") String ${id});

    @RequestMapping(value = "/bf/v1.0.0/${className}/update", method = RequestMethod.POST)
    Result<Object> update(@RequestBody ${className}Entity entity);

    @RequestMapping(value = "/bf/v1.0.0/${className}/delete", method = RequestMethod.POST)
    Result<Object> delete(@RequestParam("${id}") String ${id});

    @RequestMapping(value = "/bf/v1.0.0/${className}/list", method = RequestMethod.GET)
    Result<Object> list(
        @RequestBody ${className}Entity entity,
        @RequestParam("pageNum") int pageNum,
        @RequestParam("pageSize") int pageSize
    );
}
