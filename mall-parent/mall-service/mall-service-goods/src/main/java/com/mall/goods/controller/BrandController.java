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

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

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
//
//    @GetMapping(value = "/search/{page}/{size}")
//    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
//        //调用BrandService实现分页查询Brand
//        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
//        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
//    }
//
//    public Result<List<Brand>> findList(@RequestBody(required = false) Brand brand) {
//        //调用BrandService实现条件查询Brand
//        List<Brand> list = brandService.findList(brand);
//        return new Result<List<Brand>>(true, StatusCode.OK, "查询成功", list);
//    }

//    @DeleteMapping(value = "/{id}")
//    public Result delete(@PathVariable Integer id) {
//        //调用BrandService实现根据主键删除
//        brandService.delete(id);
//        return new Result(true, StatusCode.OK, "删除成功");
//    }
//
//
//    @PutMapping(value = "/{id}")
//    public Result update(@RequestBody Brand brand, @PathVariable Integer id) {
//        //设置主键值
//        brand.setId(id);
//        //调用BrandService实现修改Brand
//        brandService.update(brand);
//        return new Result(true, StatusCode.OK, "修改成功");
//    }
//
//
//    @PostMapping
//    public Result add(@RequestBody Brand brand) {
//        //调用BrandService实现添加Brand
//        brandService.add(brand);
//        return new Result(true, StatusCode.OK, "添加成功");
//    }
//
//
//    @GetMapping("/{id}")
//    public Result<Brand> findById(@PathVariable Integer id) {
//        //调用BrandService实现根据主键查询Brand
//        Brand brand = brandService.findById(id);
//        return new Result<Brand>(true, StatusCode.OK, "查询成功", brand);
//    }
//
//
//    @GetMapping
//    public Result<List<Brand>> findAll() {
//        try {
//            System.out.println("aaaaa=====");
//            Thread.sleep(3000);
//            System.out.println("bbbb=====");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //调用BrandService实现查询所有Brand
//        List<Brand> list = brandService.findAll();
//        return new Result<List<Brand>>(true, StatusCode.OK, "查询成功", list);
//    }
//
//
//    @GetMapping("/category/{id}")
//    public Result<List<Brand>> findBrandByCategory(@PathVariable(name = "id") Integer id) {
//        List<Brand> brandList = brandService.findByCategory(id);
//
//        return new Result<List<Brand>>(true, StatusCode.OK, "查询品牌列表成功", brandList);
//
//    }
}
