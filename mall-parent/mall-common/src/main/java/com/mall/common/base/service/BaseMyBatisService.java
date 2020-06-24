package com.mall.common.base.service;

import java.util.List;

/**
 * <p>
 * 描述: 公共业务层接口
 * </p>
 * <p>
 * 创建时间: 2019-11-15 09:23
 * </p>
 *
 * @param <T> 标签
 * @author yanglin
 */
public interface BaseMyBatisService<T> {

    List<T> findAll();
}
