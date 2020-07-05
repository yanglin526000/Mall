package com.mall.goods.controller;

import com.mall.common.base.controller.BaseHibernateController;
import com.mall.goods.pojo.Template;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Template
 * </p>
 *
 * @author yanglin
 * @date 2020-07-05 23:43:13
 */
@RestController
@RequestMapping("/template")
public class TemplateController extends BaseHibernateController<Template> {
}
