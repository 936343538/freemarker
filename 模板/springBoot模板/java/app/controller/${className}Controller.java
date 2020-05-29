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

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result<Object> insert(${className}Entity entity) {
        try {
            Result<Object> resultBf = ${className?uncap_first}Service.insert(entity);
            if (!resultBf.isSuccess()) {
                result.setCode(resultBf.getCode());
            }
        } catch (Exception e) {
            logger.error("${className}Controller insert error ", e);
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
            } else {
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

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public Result<Object> remove(String ${id}) {
        try {
            Result<Object> resultBf = ${className?uncap_first}Service.remove(${id});
            if (!resultBf.isSuccess()) {
                result.setCode(resultBf.getCode());
            }
        } catch (Exception e) {
            logger.error("${className}Controller remove error ", e);
            result.setCode(BaseRetCode.ERROR);
        }
        return result;
    }

    @RequestMapping(value = "selectPageWithParam")
    public Result<Object> selectPageWithParam(${className}Entity entity) {
        try {
            Result<Object> resultBf = ${className?uncap_first}Service.selectPageWithParam(entity, pageNum, pageSize);
            if (resultBf.isSuccess()) {
                result.setInfo(resultBf.getInfo());
            } else {
                result.setCode(resultBf.getCode());
            }
        } catch (Exception e) {
            logger.error("${className}Controller selectPageWithParam error ", e);
            result.setCode(BaseRetCode.TIME_OUT);
        }
        return result;
    }

}
