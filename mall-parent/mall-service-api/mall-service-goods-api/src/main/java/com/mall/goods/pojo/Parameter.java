package com.mall.goods.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * Parameter
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 13:58:12
 */
@Data
@Entity
@Table(name = "parameter")
public class Parameter extends CommonPo {

    @Column(name = "options")
    private String options;

    @Column(name = "template_id")
    private String templateId;

}
