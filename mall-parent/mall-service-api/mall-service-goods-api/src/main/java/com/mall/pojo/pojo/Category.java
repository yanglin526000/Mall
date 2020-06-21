package com.mall.pojo.pojo;

import com.mall.pojo.pojo.common.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * Category
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 12:19:59
 */
@Data
@Entity
@Table(name = "category")
public class Category extends CommonPo {

    @Column(name = "goods_num")
    private Integer goodsNum;

    @Column(name = "is_show")
    private Byte isShow;

    @Column(name = "is_menu")
    private String isMenu;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "template_id")
    private Integer templateId;

}
