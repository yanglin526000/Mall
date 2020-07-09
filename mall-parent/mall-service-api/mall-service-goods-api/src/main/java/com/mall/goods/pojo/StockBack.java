package com.mall.goods.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>
 * StockBack
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 14:15:59
 */
@Data
@Entity
@Table(name = "stock_back")
public class StockBack extends CommonPo {

    @Column(name = "order_id")
    private String orderId; //订单id

    @Column(name = "num")
    private Integer num; //回滚数量

    @Column(name = "status")
    private Byte status; //回滚状态

    @Column(name = "back_time")
    private Date backTime; //回滚时间

}
