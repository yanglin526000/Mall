package com.mall.common.base.controller;

import com.mall.common.base.service.BaseMyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * Base Mybatis Controller
 * </p>
 *
 * @author yanglin
 * @date 2020-06-22 19:10:40
 */
public abstract class BaseMyBatisController<T> {

    @Autowired
    private BaseMyBatisService<T> baseMyBatisService;

    //    /**
    //     * <p>
    //     * Save
    //     * </p>
    //     *
    //     * @param t T
    //     * @return org.springframework.http.ResponseEntity<java.lang.Object>
    //     * @author yanglin
    //     * @date 2020-06-22 19:11:45
    //     */
    //    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    //    public ResponseEntity<Object> save(@RequestBody T t) throws SecurityException, IllegalArgumentException {
    //        return ResponseEntity.ok(baseMyBatisService.findAll());
    //    }

    /**
     * <p>
     * findAll
     * </p>
     *
     * @return org.springframework.http.ResponseEntity<java.lang.Object>
     * @author yanglin
     * @date 2020-06-23 16:53:31
     */
    @GetMapping("list")
    public ResponseEntity<Object> findAll() throws SecurityException, IllegalArgumentException {
        return ResponseEntity.ok(baseMyBatisService.findAll());
    }


}
