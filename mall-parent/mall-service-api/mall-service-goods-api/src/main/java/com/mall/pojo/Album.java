package com.mall.pojo;

import com.mall.pojo.common.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * Album
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 11:50:19
 */
@Data
@Entity
@Table(name = "album")
public class Album extends CommonPo {

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_items")
    private String imageItems;

}
