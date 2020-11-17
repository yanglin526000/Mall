package com.mall.user.controller;

import com.mall.common.base.controller.BaseHibernateController;
import com.mall.user.pojo.UndoLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * UndoLogController
 * </p>
 *
 * @author yanglin
 * @date 2020-11-18 00:07:36
 */
@RestController
@RequestMapping("/undo-Log")
public class UndoLogController extends BaseHibernateController<UndoLog> {
}
