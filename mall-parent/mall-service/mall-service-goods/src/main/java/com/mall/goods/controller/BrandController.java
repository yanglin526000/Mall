package com.mall.goods.controller;

import com.mall.common.base.controller.BaseHibernateController;
import com.mall.goods.pojo.Brand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Brand
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 17:58:25
 */
@RestController
@RequestMapping("/brand")
public class BrandController extends BaseHibernateController<Brand> {
}
