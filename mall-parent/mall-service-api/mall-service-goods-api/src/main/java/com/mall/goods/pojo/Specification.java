package com.mall.goods.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * Specification
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 14:09:39
 */
@Data
@Entity
@Table(name = "specification")
public class Specification extends CommonPo {

    @Column(name = "options")
    private String options; //规格选项

    @Column(name = "template_id")
    private Long templateId; //模板ID

}
