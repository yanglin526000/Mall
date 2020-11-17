package com.mall.user.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * Cities
 * </p>
 *
 * @author yanglin
 * @date 2020-11-17 23:21:00
 */
@Data
@Entity
@Table(name = "cities")
public class Cities extends CommonPo {

    @Column(name = "code")
    private String code;//代码

}
