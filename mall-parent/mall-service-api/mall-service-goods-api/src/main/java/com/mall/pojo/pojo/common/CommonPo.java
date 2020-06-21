package com.mall.pojo.pojo.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * <p>
 * Common POJO
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 10:57:17
 */
@Data
@MappedSuperclass
public class CommonPo {

    @ApiModelProperty(example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ApiModelProperty(example = "Name")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(hidden = true)
    @Column(name = "create_user_id", columnDefinition = "INT UNSIGNED")
    private Long createUserId;

    @ApiModelProperty(hidden = true)
    @Column(name = "update_user_id", columnDefinition = "INT UNSIGNED")
    private Long updateUserId;

    @ApiModelProperty(hidden = true)
    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

    @ApiModelProperty(hidden = true)
    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

    @ApiModelProperty(example = "Remark Description")
    @Column(name = "remark")
    private String remark;

    @Column(name = "seq", columnDefinition = "INT UNSIGNED")
    private Long seq;

}
