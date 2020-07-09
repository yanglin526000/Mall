package com.mall.goods.pojo;

import com.mall.common.base.pojo.CommonPo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

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
    private String parentId;

    @Column(name = "template_id")
    private String templateId;

    @ApiModelProperty(hidden = true)
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "template_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Template template;

    @ApiModelProperty(hidden = true)
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "category_brand",
            inverseJoinColumns = {@JoinColumn(name = "brand_id", referencedColumnName = "id")},
            joinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")}
    )
    private List<Brand> brandList;

}
