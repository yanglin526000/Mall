package com.mall.pojo;

import com.mall.pojo.common.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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


}
