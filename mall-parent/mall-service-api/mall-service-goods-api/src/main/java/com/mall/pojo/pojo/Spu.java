package com.mall.pojo.pojo;

import com.mall.pojo.pojo.common.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * Spu
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 14:14:44
 */
@Data
@Entity
@Table(name = "spu")
public class Spu extends CommonPo {

    @Column(name = "sn")
    private String sn; //货号

    @Column(name = "caption")
    private String caption; //副标题

    @Column(name = "brand_id")
    private Long brandId; //品牌ID

    @Column(name = "category1_id")
    private Long category1Id; //一级分类

    @Column(name = "category2_id")
    private Long category2Id; //二级分类

    @Column(name = "category3_id")
    private Long category3Id; //三级分类

    @Column(name = "template_id")
    private Long templateId; //模板ID

    @Column(name = "freight_id")
    private Long freightId; //运费模板id

    @Column(name = "image")
    private String image; //图片

    @Column(name = "images")
    private String images; //图片列表

    @Column(name = "sale_service")
    private String saleService; //售后服务

    @Column(name = "introduction")
    private String introduction; //介绍

    @Column(name = "spec_items")
    private String specItems; //规格列表

    @Column(name = "para_items")
    private String paraItems; //参数列表

    @Column(name = "sale_num")
    private Long saleNum; //销量

    @Column(name = "comment_num")
    private Long commentNum; //评论数

    @Column(name = "is_marketable")
    private Byte isMarketable; //是否上架

    @Column(name = "is_enable_spec")
    private Byte isEnableSpec; //是否启用规格

    @Column(name = "status")
    private Byte status; //审核状态

}
