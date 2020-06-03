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

    /**
     * 添加
     *
     * @param entity 实体类
     * @return
     */
    @RequestMapping(value = "/bf/v1.0.0/${className}/insert", method = RequestMethod.POST)
    Result<Object> insert(@RequestBody ${className}Entity entity);

    /**
     * 通过主键ID查询
     *
     * @param ${id} 主键ID
     * @return
     */
    @RequestMapping(value = "/bf/v1.0.0/${className}/get", method = RequestMethod.GET)
    Result<Object> get(@RequestParam("entityId") String ${id});

    /**
     * 修改
     *
     * @param entity 实体类
     * @return
     */
    @RequestMapping(value = "/bf/v1.0.0/${className}/update", method = RequestMethod.POST)
    Result<Object> update(@RequestBody ${className}Entity entity);

    /**
     * 通过主键ID删除
     *
     * @param entityId 主键ID
     * @return
     */
    @RequestMapping(value = "/bf/v1.0.0/${className}/remove", method = RequestMethod.POST)
    Result<Object> remove(@RequestParam("entityId") String entityId);

    /**
     * 列表查询
     *
     * @param entity   实体类
     * @param pageNum  当前页
     * @param pageSize 每页查询条数
     * @return
     */
    @RequestMapping(value = "/bf/v1.0.0/${className}/selectPageWithParam", method = RequestMethod.GET)
    Result<Object> selectPageWithParam(
        @RequestBody ${className}Entity entity,
        @RequestParam("pageNum") int pageNum,
        @RequestParam("pageSize") int pageSize
    );
}
