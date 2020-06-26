package com.mall.common.base.controller;

import com.mall.common.base.service.BaseHibernateService;
import com.mall.common.utils.ConstantUtil;
import com.mall.common.utils.ParamUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自增主键", required = true, dataType = "String", paramType = "path")})
    @PutMapping("{id}")
    public ResponseEntity<T> update(@PathVariable Object id, @RequestBody T t) throws SecurityException, IllegalArgumentException {
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
    @SuppressWarnings({"unchecked", "rawtypes"})
    @ApiOperation(value = "根据id删除（自动生成）", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自增主键", required = true, dataType = "String", paramType = "path")})
    @DeleteMapping("{id}")
    public ResponseEntity<T> delete(@PathVariable Object id)
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
    @SuppressWarnings({"unchecked", "rawtypes"})
    @ApiOperation(value = "根据id对象信息（自动生成）", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自增主键", required = true, dataType = "String", paramType = "path")})
    @GetMapping("{id}")
    public ResponseEntity<T> info(@PathVariable Object id) throws Exception {
        T t = (T) ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0])
                .getDeclaredConstructor().newInstance();
        ParamUtil.putField(t, "id", id);
        return ResponseEntity.ok(baseHibernateService.info(t));
    }

    /**
     * <p>
     * Get List By Conditions
     * </p>
     *
     * @param t    T
     * @param page Integer
     * @param size Integer
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author yanglin
     * @date 2020-06-12 19:30:16
     */
    @ApiOperation(value = "根据对象条件查询分页列表（自动生成）", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数，默认第一页为"
                    + ConstantUtil.DEFAULT_PAGE_INDEX, required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页显示数量，默认每页数量为"
                    + ConstantUtil.DEFAULT_PAGE_SIZE, required = false, dataType = "String", paramType = "query")})
    @GetMapping
    public ResponseEntity<Object> list(T t,
                                       @RequestParam(value = "page", required = false, defaultValue = ConstantUtil.DEFAULT_PAGE_INDEX) Integer page,
                                       @RequestParam(value = "size", required = false, defaultValue = ConstantUtil.DEFAULT_PAGE_SIZE) Integer size) {
        Map<String, Object> result = new HashMap<>(ConstantUtil.RESULT_MAP_INIT_COUNT);
        Map<String, Object> r = baseHibernateService.list(t, PageRequest.of(page, size));
        result.put("data", r.get("list"));
        result.put("pageInfo", r.get("pageInfo"));
        return ResponseEntity.ok(result);
    }

}
