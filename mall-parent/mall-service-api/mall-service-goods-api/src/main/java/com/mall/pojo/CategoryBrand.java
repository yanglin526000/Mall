package com.mall.pojo;

import com.mall.pojo.common.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/****
 * @Author:admin
 * @Description:CategoryBrand构建
 * @Date 2019/6/14 19:13
 *****/
@Data
@Entity
@Table(name = "category_brand")
public class CategoryBrand extends CommonPo {

    @Id
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "brand_id")
    private Integer brandId;

}
