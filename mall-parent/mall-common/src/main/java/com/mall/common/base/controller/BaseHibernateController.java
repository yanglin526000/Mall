package com.mall.common.base.controller;

import com.mall.common.base.service.BaseHibernateService;
import com.mall.common.utils.ConstantUtil;
import com.mall.common.utils.ParamUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * The common controller implementation class - Hibernate
 * </p>
 *
 * @author yanglin
 * @date 2020-06-12 18:44:20
 */
public abstract class BaseHibernateController<T> {

    @Autowired
    private BaseHibernateService<T> baseHibernateService;

    /**
     * <p>
     * Add
     * </p>
     *
     * @param t T
     * @return org.springframework.http.ResponseEntity<T>
     * @throws
     * @author yanglin
     * @date 2020-06-15 20:32:05
     */
    @PostMapping
    public ResponseEntity<T> add(@RequestBody T t) throws SecurityException, IllegalArgumentException {
        return ResponseEntity.ok(baseHibernateService.save(t));
    }

    /**
     * <p>
     * Update
     * </p>
     *
     * @param t T
     * @return org.springframework.http.ResponseEntity<T>
     * @throws
     * @author yanglin
     * @date 2020-06-15 20:32:05
     */
    @PutMapping("{id}")
    public ResponseEntity<T> update(@PathVariable String id, @RequestBody T t) throws SecurityException, IllegalArgumentException {
        ParamUtil.putField(t, "id", id);
        return ResponseEntity.ok(baseHibernateService.save(t));
    }

    /**
     * <p>
     * Delete By Id
     * </p>
     *
     * @param id Primary Key
     * @return org.springframework.http.ResponseEntity<T>
     * @author yanglin
     * @date 2020-06-15 20:33:17
     */
    @SuppressWarnings({"unchecked"})
    @DeleteMapping("{id}")
    public ResponseEntity<T> deleteById(@PathVariable String id)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        T t = (T) ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0])
                .getDeclaredConstructor().newInstance();
        ParamUtil.putField(t, "id", id);
        return ResponseEntity.ok(baseHibernateService.delete(t));
    }

    /**
     * <p>
     * Get Info By Id
     * </p>
     *
     * @param id Primary Key
     * @return org.springframework.http.ResponseEntity<T>
     * @author yanglin
     * @date 2020-06-15 20:37:16
     */
    @SuppressWarnings({"unchecked"})
    @GetMapping("{id}")
    public ResponseEntity<T> getInfoById(@PathVariable String id) throws Exception {
        T t = (T) ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0])
                .getDeclaredConstructor().newInstance();
        ParamUtil.putField(t, "id", id);
        return ResponseEntity.ok(baseHibernateService.info(t));
    }

    /**
     * <p>
     * Fuzzy Query
     * </p>
     *
     * @param t    T
     * @param page Integer
     * @param size Integer
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author yanglin
     * @date 2020-06-12 19:30:16
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数，默认第一页为"
                    + ConstantUtil.DEFAULT_PAGE_INDEX, required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页显示数量，默认每页数量为"
                    + ConstantUtil.DEFAULT_PAGE_SIZE, required = false, dataType = "String", paramType = "query")})
    @GetMapping("fuzzyQuery")
    public ResponseEntity<Object> fuzzyQuery(T t,
                                             @RequestParam(value = "page", required = false, defaultValue = ConstantUtil.DEFAULT_PAGE_INDEX) Integer page,
                                             @RequestParam(value = "size", required = false, defaultValue = ConstantUtil.DEFAULT_PAGE_SIZE) Integer size) {
        Map<String, Object> result = new HashMap<>(ConstantUtil.RESULT_MAP_INIT_COUNT);
        Map<String, Object> r = baseHibernateService.list(t, PageRequest.of(page, size));
        result.put("data", r.get("list"));
        result.put("pageInfo", r.get("pageInfo"));
        return ResponseEntity.ok(result);
    }

    /**
     * <p>
     * Exact Query
     * </p>
     *
     * @param t    T
     * @param page Integer
     * @param size Integer
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author yanglin
     * @date 2020-06-12 19:30:16
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数，默认第一页为"
                    + ConstantUtil.DEFAULT_PAGE_INDEX, required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页显示数量，默认每页数量为"
                    + ConstantUtil.DEFAULT_PAGE_SIZE, required = false, dataType = "String", paramType = "query")})
    @GetMapping("exactSearch")
    public ResponseEntity<Object> exactSearch(T t,
                                              @RequestParam(value = "page", required = false, defaultValue = ConstantUtil.DEFAULT_PAGE_INDEX) Integer page,
                                              @RequestParam(value = "size", required = false, defaultValue = ConstantUtil.DEFAULT_PAGE_SIZE) Integer size) {
        Map<String, Object> result = new HashMap<>(ConstantUtil.RESULT_MAP_INIT_COUNT);
        Map<String, Object> r = baseHibernateService.listAccurate(t, PageRequest.of(page, size));
        result.put("data", r.get("list"));
        result.put("pageInfo", r.get("pageInfo"));
        return ResponseEntity.ok(result);
    }

    /**
     * <p>
     * Batch Add
     * </p>
     *
     * @param t
     * @return org.springframework.http.ResponseEntity<java.lang.Object>
     * @author yanglin
     * @date 2020-07-08 17:58:39
     */
    @PostMapping("batchAdd")
    public ResponseEntity<List<T>> batchAdd(@RequestBody List<T> t) {
        List<T> result = baseHibernateService.batchAdd(t);
        return ResponseEntity.ok(result);
    }

}
