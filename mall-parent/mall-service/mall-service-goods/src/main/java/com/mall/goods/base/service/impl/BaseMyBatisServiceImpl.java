package com.mall.goods.base.service.impl;

import com.mall.goods.base.service.BaseMyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.*;

import java.util.List;

/**
 * <p>
 * 描述: 公共业务层接口实现
 * </p>
 * <p>
 * 创建时间: 2019-11-15 09:35
 * </p>
 *
 * @param <T> 标签
 * @author yanglin
 */
@Service
public class BaseMyBatisServiceImpl<T> implements BaseMyBatisService<T> {

    @Autowired
    BaseMapper<T> baseMapper;
    @Autowired
    ConditionMapper<T> conditionMapper;
    @Autowired
    ExampleMapper<T> exampleMapper;
    @Autowired
    IdsMapper<T> idsMapper;
    @Autowired
    MySqlMapper<T> mySqlMapper;
    //    @Autowired
    //    SqlServerMapper<T> sqlServerMapper;
    //    @Autowired
    //    RowBoundsMapper<T> rowBoundsMapper;


    //    @Autowired
    //    BaseMapper<T> baseMapper;

    @Override
    public List<T> findAll() {
        //        int id = baseBatisMapper.insert(t);
        //        return baseBatisMapper.selzectByPrimaryKey(id);
        return baseMapper.selectAll();
    }


}
