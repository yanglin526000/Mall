package com.mall.pojo;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * Goods
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 13:57:35
 */
@Data
public class Goods {

    private Spu spu;
    private List<Sku> skuList;

}
