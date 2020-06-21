package com.mall.pojo.pojo;

import com.mall.pojo.pojo.common.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * Brand
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 12:03:14
 */
@Data
@Entity
@Table(name = "brand")
public class Brand extends CommonPo {

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "letter")
    private String letter;

}
