package com.mall.goods.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * Category Brand
 * </p>
 *
 * @author yanglin
 * @date 2020-07-06 20:42:28
 */
@Data
@Entity
@Table(name = "category_brand")
public class CategoryBrand extends CommonPo {

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "brand_id")
    private Integer brandId;

}
