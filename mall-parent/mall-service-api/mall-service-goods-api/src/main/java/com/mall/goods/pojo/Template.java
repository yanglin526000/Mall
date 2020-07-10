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
 * Template
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 14:18:35
 */
@Data
@Entity
@Table(name = "template")
public class Template extends CommonPo {

    @Column(name = "spec_num")
    private Integer specNum; //规格数量

    @Column(name = "para_num")
    private Integer paraNum; //参数数量

    @ApiModelProperty(hidden = true)
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<Specification> specificationList;

}
