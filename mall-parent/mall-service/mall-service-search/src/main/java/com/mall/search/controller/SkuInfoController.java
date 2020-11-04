package com.mall.search.controller;

import com.mall.search.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class SkuInfoController {

    @Autowired
    private SkuService skuService;

    @GetMapping("/import")
    public ResponseEntity<Object> importEs() {
        skuService.importEs();
        return ResponseEntity.ok("成功");
    }

    /**
     * <p>
     * {
     * "keyInput":
     * {
     * "keywords":"小米",
     * "category":"手机",
     * "brand":"小米",
     * "price":"0-500"
     * },
     * "pageInfo":
     * {
     * "pageNum":0,
     * "pageSize":20
     * },
     * "sortInfo":
     * [
     * {
     * "field":"status",
     * "rule":"ASC"
     * },
     * {
     * "field":"price",
     * "rule":"DESC"
     * }
     * ]
     * }
     * </p>
     *
     * @author yanglin
     * @date 2020-11-04 22:35:30
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> search(@RequestBody(required = false) Map<String, Object> searchMap) {
        Map<String, Object> result = skuService.search(searchMap);
        return ResponseEntity.ok(result);
    }
}
