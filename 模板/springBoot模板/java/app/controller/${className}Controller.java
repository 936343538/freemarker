package bf.app.service.controller;

import bf.app.service.basecontroller.BaseController;
import bf.app.service.bfdata.I${className}Service;
import bf.data.entity.entity.${className}Entity;
import bf.data.entity.resultcode.BaseRetCode;
import bf.data.entity.resultcode.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@RestController
@RequestMapping("/app/v1/${className}/")
@Scope("prototype")
public class ${className}Controller extends BaseController {

    @Autowired
    I${className}Service ${className?uncap_first}Service;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result<Object> add(${className}Entity entity) {
        try {
            Result<Object> resultBf = ${className?uncap_first}Service.add(entity);
            if (!resultBf.isSuccess()) {
                result.setCode(resultBf.getCode());
            }
        } catch (Exception e) {
            logger.error("${className}Controller add error ", e);
            result.setCode(BaseRetCode.ERROR);
        }
        return result;
    }

    @RequestMapping(value = "get")
    public Result<Object> get(String ${id}) {
        try {
            Result<Object> resultBf = ${className?uncap_first}Service.get(${id});
            if (resultBf.isSuccess()) {
                result.setInfo(resultBf.getInfo());
            }else {
                result.setCode(resultBf.getCode());
            }
        } catch (Exception e) {
            logger.error("${className}Controller get error ", e);
            result.setCode(BaseRetCode.ERROR);
        }
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result<Object> update(${className}Entity entity) {
        try {
            Result<Object> resultBf = ${className?uncap_first}Service.update(entity);
            if (!resultBf.isSuccess()) {
                result.setCode(resultBf.getCode());
            }
        } catch (Exception e) {
            logger.error("${className}Controller update error ", e);
            result.setCode(BaseRetCode.ERROR);
        }
        return result;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result<Object> delete(String ${id}) {
        try {
            Result<Object> resultBf = ${className?uncap_first}Service.delete(${id});
            if (!resultBf.isSuccess()) {
                result.setCode(resultBf.getCode());
            }
        } catch (Exception e) {
            logger.error("${className}Controller delete error ", e);
            result.setCode(BaseRetCode.ERROR);
        }
        return result;
    }

    @RequestMapping(value = "list")
    public Result<Object> list(${className}Entity entity) {
        try {
            Result<Object> resultBf = ${className?uncap_first}Service.list(entity, pageNum, pageSize);
            if (resultBf.isSuccess()) {
                result.setInfo(resultBf.getInfo());
            } else {
                result.setCode(resultBf.getCode());
            }
        } catch (Exception e) {
            logger.error("SchoolVideoController watchVideo error ", e);
            result.setCode(BaseRetCode.TIME_OUT);
        }
        return result;
    }

}
