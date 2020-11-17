package com.mall.user.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * Address
 * </p>
 *
 * @author yanglin
 * @date 2020-11-14 00:37:00
 */
@Data
@Entity
@Table(name = "address")
public class Address extends CommonPo {

    @Column(name = "username")
    private String username;//用户名

    @Column(name = "province_code")
    private String provinceId;//省

    @Column(name = "city_code")
    private String cityId;//市

    @Column(name = "area_code")
    private String areaId;//县/区

    @Column(name = "phone")
    private String phone;//电话

    @Column(name = "address")
    private String address;//详细地址

    @Column(name = "contact")
    private String contact;//联系人

    @Column(name = "is_default")
    private String isDefault;//是否是默认 1默认 0否

    @Column(name = "alias")
    private String alias;//别名

}
