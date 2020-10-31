package com.mall.goods.feign;

import com.mall.common.utils.ConstantUtil;
import com.mall.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("exactSearch")
    public ResponseEntity<Map<String, Object>> exactSearch(@RequestParam Sku t,
                                                           @RequestParam(value = "page", required = false, defaultValue = ConstantUtil.DEFAULT_PAGE_INDEX) Integer page,
                                                           @RequestParam(value = "size", required = false, defaultValue = ConstantUtil.DEFAULT_PAGE_SIZE) Integer size);
}
