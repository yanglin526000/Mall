package com.mall.goods.controller;

import com.mall.common.base.controller.BaseHibernateController;
import com.mall.goods.pojo.Parameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Parameter
 * </p>
 *
 * @author yanglin
 * @date 2020-07-05 23:43:13
 */
@RestController
@RequestMapping("/parameter")
public class ParameterController extends BaseHibernateController<Parameter> {
}
