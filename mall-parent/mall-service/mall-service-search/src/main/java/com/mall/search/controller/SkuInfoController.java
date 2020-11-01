package com.mall.search.controller;

import com.mall.search.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/search")
public class SkuInfoController {

    @Autowired
    private SkuService skuService;

    @GetMapping("/import")
    public ResponseEntity<Object> importEs() {
        skuService.importEs();
        return ResponseEntity.ok("成功");
    }

}
