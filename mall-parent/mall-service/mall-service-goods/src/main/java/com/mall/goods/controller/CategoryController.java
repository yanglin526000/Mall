package com.mall.goods.controller;

import com.mall.common.base.controller.BaseHibernateController;
import com.mall.goods.pojo.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Category
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 17:58:25
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseHibernateController<Category> {
}
