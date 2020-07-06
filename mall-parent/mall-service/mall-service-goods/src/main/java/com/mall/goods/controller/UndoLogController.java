package com.mall.goods.controller;

import com.mall.common.base.controller.BaseHibernateController;
import com.mall.goods.pojo.UndoLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Undo Log
 * </p>
 *
 * @author yanglin
 * @date 2020-07-05 23:43:13
 */
@RestController
@RequestMapping("/undo-log")
public class UndoLogController extends BaseHibernateController<UndoLog> {
}
