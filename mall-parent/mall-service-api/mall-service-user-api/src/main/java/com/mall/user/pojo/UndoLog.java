package com.mall.user.pojo;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * UndoLog
 * </p>
 *
 * @author yanglin
 * @date 2020-11-17 23:29:18
 */
@Data
@Entity
@Table(name = "undo_log")
public class UndoLog extends CommonPo {

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "xid")
    private String xid;

    @Column(name = "rollback_info")
    private String rollbackInfo;

    @Column(name = "log_status")
    private Byte logStatus;

    @Column(name = "ext")
    private String ext;

}
