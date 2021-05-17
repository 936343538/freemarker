package ${package}.service.impl;

import bf.data.entity.utils.CodeUtils;
import cn.hutool.core.util.ObjectUtil;
import ${package}.entity.${className}Entity;
import ${package}.entity.mapper.${className}EntityMapper;
import ${package}.entity.request.${className}Query;
import ${package}.mapper.${className}Mapper;
import ${package}.service.${className}Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@Service
public class ${className}ServiceImpl
        extends BaseServiceImpl<${className}EntityMapper, ${className}Mapper, ${className}Entity>
        implements ${className}Service {

        /**
         * 对象添加
         *
         * @param entity
         * @return
         */
        @Override
        public Boolean insert(${className}Entity entity) {
                entity.set${id?cap_first}(CodeUtils.uuid32());
                return super.insert(entity);
        }

        /**
         * 分页查询
         *
         * @param pageNum
         * @param pageSize
         * @param query
         * @return
         */
        @Override
        public PageInfo<${className}Entity> listPage(int pageNum, int pageSize, ${className}Query query) {
                PageHelper.startPage(pageNum, pageSize);
                return list(query);
        }

        /**
         * 查询列表
         *
         * @param query
         * @return
         */
        @Override
        public PageInfo<${className}Entity> list(${className}Query query) {
                Page<${className}Entity> page = baseMapper.selectPageWithParam(query);
                return page.toPageInfo();
        }

        /**
         * 逻辑删除
         *
         * @param idList
         */
        @Override
        @Transactional
        public void deleteBatch(List<String> idList) {
                if (ObjectUtil.isNotEmpty(idList)) {
                        //逻辑删除
                        baseMapper.deleteBatch(idList);
                }
        }
}
