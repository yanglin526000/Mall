package com.mall.goods.dao;

import com.mall.goods.mapper.MyBatisMapper;
import com.mall.pojo.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * BrandMapper
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 15:22:22
 */
@Mapper
public interface BrandMapper extends MyBatisMapper<Brand> {

    @Select(value = "select tb.* from tb_brand tb ,tb_category_brand tbc where tb.id = tbc.brand_id and tbc.category_id=#{categoryid}")
    List<Brand> findByCategory(Integer categoryid);
}
