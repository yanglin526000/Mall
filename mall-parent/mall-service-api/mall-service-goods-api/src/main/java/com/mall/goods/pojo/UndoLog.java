package com.mall.goods.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>
 * UndoLog
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 14:19:20
 */
@Data
@Entity
@Table(name = "undo_log")
public class UndoLog extends CommonPo {

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "xid")
    private Long xid;

    @Column(name = "rollback_info")
    private String rollbackInfo;

    @Column(name = "log_status")
    private Byte logStatus;

    @Column(name = "log_created")
    private Date logCreated;

    @Column(name = "log_modified")
    private Date logModified;

    @Column(name = "ext")
    private String ext;


}
