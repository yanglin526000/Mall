package com.mall.goods.controller;

import com.mall.goods.base.controller.BaseMyBatisController;
import com.mall.pojo.Brand;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Brand Controller
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 17:58:25
 */
@RestController
@RequestMapping("/brand")
@CrossOrigin
public class BrandController extends BaseMyBatisController<Brand> {


}
