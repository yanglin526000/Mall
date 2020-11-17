package com.mall.user.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * Provinces
 * </p>
 *
 * @author yanglin
 * @date 2020-11-17 23:25:44
 */
@Data
@Entity
@Table(name = "provinces")
public class Provinces extends CommonPo {

    @Column(name = "code")
    private String code;//代码

}
