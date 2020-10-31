package com.mall.search.controller;

import com.mall.goods.feign.SkuFeign;
import com.mall.goods.pojo.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/*
 * <p>
 * 用于接收页面传递的请求 来测试 导入数据
 * 实现搜索的功能
 * </p>
 *
 * @author yanglin
 * @date 2020-10-31 17:21:09
 */
@RestController
@CrossOrigin
@RequestMapping("/search")
public class SkuController {

    @Autowired
    private SkuFeign skuFeign;

    @GetMapping("/import")
    public ResponseEntity<Map<String, Object>> importEs() {
        ResponseEntity<Map<String, Object>> r = skuFeign.exactSearch(new Sku(), 0, 100);
        return r;
    }

}
