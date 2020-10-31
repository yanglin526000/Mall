package com.mall.search.pojo;

import com.mall.goods.pojo.Sku;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 1.创建索引
 * 2.创建类型
 * 3.创建文档 ()
 * 4.字段的映射(是否分词 是否索引 是否存储  数据类型是什么 分词器是什么)
 * </p>
 *
 * @author yanglin
 * @date 2020-10-31 17:28:07
 */
@Data
@Document(indexName = "skuinfo")
public class SkuInfo extends Sku {

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

    //动态的域的添加和变化
    //规格参数
    //@Field(type = FieldType.Object)
    private Map<String, Object> specMap;

}
