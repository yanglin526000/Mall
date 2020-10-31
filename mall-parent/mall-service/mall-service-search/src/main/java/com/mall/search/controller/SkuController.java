package com.mall.search.controller;

import com.mall.goods.feign.SkuFeign;
import com.mall.goods.pojo.Sku;
import com.mall.search.pojo.SkuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
@RequestMapping("/search")
public class SkuController {

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/import")
    public ResponseEntity<Map<String, Object>> importEs() {
        ResponseEntity<Map<String, Object>> r = skuFeign.exactSearch(new Sku(), 0, 100);
        List<SkuInfo> skuInfoList = (List<SkuInfo>) r.getBody().get("data");
        return r;
    }

}
