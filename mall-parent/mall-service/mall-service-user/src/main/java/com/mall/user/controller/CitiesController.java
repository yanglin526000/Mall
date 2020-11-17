package com.mall.user.controller;

import com.mall.common.base.controller.BaseHibernateController;
import com.mall.user.pojo.Cities;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Cities
 * </p>
 *
 * @author yanglin
 * @date 2020-11-18 00:07:36
 */
@RestController
@RequestMapping("/cities")
public class CitiesController extends BaseHibernateController<Cities> {
}
