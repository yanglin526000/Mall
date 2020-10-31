package com.mall.goods.feign;

import com.mall.common.utils.ConstantUtil;
import com.mall.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Sku Feign
 * </p>
 *
 * @author yanglin
 * @date 2020-10-31 17:26:46
 */
@FeignClient(value = "goods")
@RequestMapping("/sku")
public interface SkuFeign {

    @PostMapping
    ResponseEntity<Sku> add(@RequestBody Sku t);

    @PutMapping("{id}")
    ResponseEntity<Sku> update(@PathVariable String id, @RequestBody Sku t);

    @DeleteMapping("{id}")
    ResponseEntity<Sku> deleteById(@PathVariable String id);

    @GetMapping("{id}")
    ResponseEntity<Sku> getInfoById(@PathVariable String id);

    @GetMapping("fuzzyQuery")
    ResponseEntity<Map<String, Object>> fuzzyQuery(@RequestParam Sku t,
                                                   @RequestParam(value = "page", required = false, defaultValue = ConstantUtil.DEFAULT_PAGE_INDEX) Integer page,
                                                   @RequestParam(value = "size", required = false, defaultValue = ConstantUtil.DEFAULT_PAGE_SIZE) Integer size);

    @GetMapping("exactSearch")
    ResponseEntity<Map<String, Object>> exactSearch(@RequestParam Sku t,
                                                    @RequestParam(value = "page", required = false, defaultValue = ConstantUtil.DEFAULT_PAGE_INDEX) Integer page,
                                                    @RequestParam(value = "size", required = false, defaultValue = ConstantUtil.DEFAULT_PAGE_SIZE) Integer size);

    @PostMapping("batchAdd")
    ResponseEntity<List<Sku>> batchAdd(@RequestBody List<Sku> t);

    @DeleteMapping("deleteByCond")
    ResponseEntity<List<Sku>> deleteByCond(@RequestBody Sku t);
}
