package ${package}.controller;

import cn.hutool.core.util.StrUtil;
import ${package}.entity.${className}Entity;
import ${package}.entity.request.${className}Query;
import ${package}.enums.StatusEnum;
import ${package}.resultcode.Result;
import ${package}.service.${className}Service;
import ${package}.version.ApiVersion;
import com.github.pagehelper.PageInfo;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@RestController
@RequestMapping(value = "/datas/{version}/${className}")
@DefaultProperties(defaultFallback = "defaultFallback")
public class ${className}Controller extends BaseController<${className}Service, ${className}Entity> {

    /**
     * 添加
     *
     * @param query
     * @return
     */
    @ApiVersion(V1)
    @HystrixCommand
    @RequestMapping(value = "/insert")
    public Result<Object> insert(${className}Query query) {
        dService.insert(query.insertBuild());
        return new Result<>(StatusEnum.SUCCESS);
    }

    /**
     * 根据ID查询
     *
     * @param query
     * @return
     */
    @ApiVersion(V1)
    @HystrixCommand
    @RequestMapping
    public Result<Object> get(${className}Query query) {
        if (StrUtil.isBlank(query.get${id?cap_first}())) {
            return new Result<>(StatusEnum.PARAM_NOT_NULL);
        }
        ${className}Entity entity = dService.get(query.get${id?cap_first}());
        return new Result<>(StatusEnum.SUCCESS, entity);
    }

    /**
     * 编辑
     *
     * @param query
     * @return
     */
    @ApiVersion(V1)
    @HystrixCommand
    @RequestMapping(value = "/edit")
    public Result<Object> edit(${className}Query query) {
        if (StrUtil.isBlank(query.get${id?cap_first}())) {
            return new Result<>(StatusEnum.PARAM_NOT_NULL);
        }
        dService.update(query.updateBuild());
        return new Result<>(StatusEnum.SUCCESS);
    }

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    @ApiVersion(V1)
    @HystrixCommand
    @RequestMapping(value = "/list/page")
    public Result<Object> listPage(${className}Query query) {
        PageInfo<${className}Entity> pageInfo = dService.listPage(pageNum, pageSize, query);
        return new Result<>(StatusEnum.SUCCESS, pageInfo);
    }

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @ApiVersion(V1)
    @HystrixCommand
    @RequestMapping(value = "/list")
    public Result<Object> list(${className}Query query) {
        PageInfo<${className}Entity> pageInfo = dService.list(query);
        return new Result<>(StatusEnum.SUCCESS, pageInfo);
    }

    /**
     * 逻辑删除
     *
     * @param query 多值用","隔开
     * @return
     */
    @ApiVersion(V1)
    @HystrixCommand
    @RequestMapping(value = "/delete")
    public Result<Object> delete(${className}Query query) {
        if (StrUtil.isBlank(query.get${id?cap_first}())) {
            return new Result<>(StatusEnum.PARAM_NOT_NULL);
        }
        dService.deleteBatch(query.deleteInit());
        return new Result<>(StatusEnum.SUCCESS);
    }

}
