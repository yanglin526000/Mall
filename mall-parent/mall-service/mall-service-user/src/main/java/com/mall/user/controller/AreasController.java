package com.mall.user.controller;

import com.mall.common.base.controller.BaseHibernateController;
import com.mall.user.pojo.Areas;
import com.mall.user.pojo.Cities;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * AreasController
 * </p>
 *
 * @author yanglin
 * @date 2020-11-18 00:07:36
 */
@RestController
@RequestMapping("/areas")
public class AreasController extends BaseHibernateController<Areas> {
}
