package com.mall.goods.controller;

import com.mall.common.base.controller.BaseHibernateController;
import com.mall.goods.pojo.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Specification
 * </p>
 *
 * @author yanglin
 * @date 2020-07-06 00:08:25
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController extends BaseHibernateController<Specification> {
}
