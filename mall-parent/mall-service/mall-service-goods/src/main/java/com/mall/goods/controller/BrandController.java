package com.mall.goods.controller;

import com.mall.goods.service.BrandService;
import com.mall.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
public class BrandController {

    @Autowired
    private BrandService brandService;


    /**
     * <p>
     * findAll
     * </p>
     *
     * @param
     * @return org.springframework.http.ResponseEntity<java.lang.Object>
     * @author yanglin
     * @date 2020-06-21 15:55:56
     */
    @PostMapping(value = "/all")
    public ResponseEntity<Object> findAll() {
        //调用BrandService实现分页条件查询Brand
        List<Brand> result = brandService.findAll();
        return ResponseEntity.ok(result);
    }

}
