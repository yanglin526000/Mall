package com.mall.pojo.pojo;

import com.mall.pojo.pojo.common.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * Spec
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 14:09:39
 */
@Data
@Entity
@Table(name = "spec")
public class Spec extends CommonPo {

    @Column(name = "options")
    private String options; //规格选项

    @Column(name = "template_id")
    private Long templateId; //模板ID

}
