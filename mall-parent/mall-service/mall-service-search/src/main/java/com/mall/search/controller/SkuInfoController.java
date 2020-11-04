package com.mall.search.controller;

import com.mall.search.service.SkuService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
     * @param searchMap 搜索的条件 map
     * @return resultMap  返回的结果 map
     */
    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchMap", value = "搜索条件", paramType = "body", defaultValue = "{}")})
    public ResponseEntity<Map<String, Object>> search(@RequestBody(required = false) Map<String, Object> searchMap) {
        Map<String, Object> result = skuService.search(searchMap);
        return ResponseEntity.ok(result);
    }
}
