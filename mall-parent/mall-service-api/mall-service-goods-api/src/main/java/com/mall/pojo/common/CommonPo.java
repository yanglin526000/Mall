package com.mall.pojo.common;

import com.mall.utils.ConstantUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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

    @ApiModelProperty(hidden = true)
    @Column(name = "seq", columnDefinition = "INT UNSIGNED")
    private Long seq;

    @ApiModelProperty(hidden = true)
    @Column(name = "is_delete", columnDefinition = "TINYINT(2)", nullable = false)
    private Byte isDelete = ConstantUtil.IS_NOT_DELETE;

}
