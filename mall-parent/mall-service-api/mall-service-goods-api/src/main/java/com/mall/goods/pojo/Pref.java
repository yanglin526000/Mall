package com.mall.goods.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Preferential Price
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 14:01:17
 */
@Data
@Entity
@Table(name = "pref")
public class Pref extends CommonPo {

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "buy_money")
    private BigDecimal buyMoney;

    @Column(name = "pre_money")
    private BigDecimal preMoney;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "type")
    private String type;

    @Column(name = "state")
    private String state;

}
