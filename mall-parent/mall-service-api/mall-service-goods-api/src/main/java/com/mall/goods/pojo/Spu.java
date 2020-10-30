package com.mall.goods.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String category1Id; //一级分类

    @Column(name = "category2_id")
    private String category2Id; //二级分类

    @Column(name = "category3_id")
    private String category3Id; //三级分类

    @Column(name = "template_id")
    private String templateId; //模板ID

    @Column(name = "freight_id")
    private String freightId; //运费模板id

    @Column(name = "image", columnDefinition = "TEXT")
    private String image; //图片

    @Column(name = "images", columnDefinition = "TEXT")
    private String images; //图片列表

    @Column(name = "sale_service")
    private String saleService; //售后服务

    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction; //介绍，定义TEXT类型保持有足够的长度

    @Column(name = "spec_items", columnDefinition = "TEXT")
    private String specItems; //规格列表

    @Column(name = "para_items", columnDefinition = "TEXT")
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

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "spu_id", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)
    private List<Sku> skuList = new ArrayList<>();//配置不生成外键，nullable = true, insertable = false, updatable = false

}
