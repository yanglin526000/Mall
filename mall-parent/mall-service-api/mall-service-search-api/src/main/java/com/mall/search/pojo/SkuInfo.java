package com.mall.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * indexName="索引库名"
 * shards = 分片数量(默认1)
 * replicas = 副本数量(默认1)
 * 4.字段的映射(是否分词 是否索引 是否存储  数据类型是什么 分词器是什么)
 * </p>
 *
 * @author yanglin
 * @date 2020-10-31 17:28:07
 */
@Data
@Document(indexName = "skuinfo")
public class SkuInfo {

    //@id 表示文档的唯一标识
    @Id
    private String id;

    //SKU名称
    // @Field 字段的映射
    // analyzer 指定索引的是用的分词分词器   searchAnalyzer :搜索的时候使用的分词器
    // type 指定数据类型
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String name;

    //商品价格，单位为：元
    @Field(type = FieldType.Double)
    private BigDecimal price;

    //是否默认
    private String isDefault;

    //类目名称
    // FieldType.Keyword 是一个关键字 (keyword) 表示不分词.
    @Field(type = FieldType.Keyword)
    private String categoryName;

    //品牌名称
    @Field(type = FieldType.Keyword)
    private String brandName;

    private String sn;

    private Integer num;

    private Integer alertNum; //库存预警数量

    private String image;

    private String images;

    private Integer weight;

    private String spuId;

    private String categoryId;

    private String spec; //规格

    private Long saleNum; //销量

    private Long commentNum; //评论数

    private Byte status; //商品状态 1-正常，2-下架，3-删除

    private Long createUserId;

    private Long updateUserId;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private Long seq;

    private Byte isDelete;

    //动态的域的添加和变化
    //规格参数
    //@Field(type = FieldType.Object)
    private Map<String, Object> specMap;

}
