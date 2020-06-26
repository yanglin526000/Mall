package com.mall.goods.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * <p>
 * Sku
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 14:05:44
 */
@Data
@Entity
@Table(name = "sku")
public class Sku extends CommonPo {

    @Column(name = "sn")
    private String sn;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "num")
    private Integer num;

    @Column(name = "alert_num")
    private Integer alertNum;//库存预警数量

    @Column(name = "image")
    private String image;

    @Column(name = "images")
    private String images;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "spu_id")
    private Long spuId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "spec")
    private String spec; //规格

    @Column(name = "sale_num")
    private Long saleNum; //销量

    @Column(name = "comment_num")
    private Long commentNum; //评论数

    @Column(name = "status")
    private Byte status; //商品状态 1-正常，2-下架，3-删除

}
