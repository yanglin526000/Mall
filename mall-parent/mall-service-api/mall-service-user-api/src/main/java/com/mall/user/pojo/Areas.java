package com.mall.user.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * Areas
 * </p>
 *
 * @author yanglin
 * @date 2020-11-17 23:17:33
 */
@Data
@Entity
@Table(name = "areas")
public class Areas extends CommonPo {

    @Column(name = "code")
    private String code;//代码

}
