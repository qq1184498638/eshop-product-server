package com.springcloud.eshop.product.server.controller;

import com.springcloud.eshop.common.server.support.utils.ServerResponse;
import com.springcloud.eshop.product.server.entity.Brand;
import com.springcloud.eshop.product.server.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "品牌相关的接口")
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping("/add")
    @ApiOperation("添加品牌的接口")
    public ServerResponse addBrand(@ApiParam("品牌的参数") @ModelAttribute Brand brand) {
        brandService.save(brand);
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    @DeleteMapping("/del")
    @ApiOperation("删除品牌的接口")
    public ServerResponse delBrand(@ApiParam("品牌的id") @RequestParam("id") String id) {
        brandService.delete(id);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @PutMapping("/update")
    @ApiOperation("更新品牌的接口")
    public ServerResponse updateBrand(@ModelAttribute Brand brand) {
        brandService.update(brand);
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    @GetMapping("/findById")
    @ApiOperation("根据品牌id查询的接口")
    public ServerResponse findByBrandId(@ApiParam("品牌的id") String id) {
        Brand brand = brandService.get(id);
        if (brand != null) {
            return ServerResponse.createBySuccess(brand);
        }
        return ServerResponse.createByErrorMessage("为查询到该品牌信息");
    }

    @GetMapping("/findAll")
    @ApiOperation("查询所有的品牌的接口")
    public ServerResponse findAll() {
        List<Brand> lists = brandService.getAll();
        if (CollectionUtils.isEmpty(lists)) {
            return ServerResponse.createByErrorMessage("目前商品列表为空");
        }
        return ServerResponse.createBySuccess(lists);
    }

    @GetMapping("/findByIds")
    @ApiOperation("根据id进行批量查询")
    public ServerResponse findByIds(@RequestParam("ids") String[] ids) {
        List<Brand> list = brandService.findByIds(ids);
        if (list.isEmpty()) {
            return ServerResponse.createByErrorMessage("根据品牌ids, 未查询出结果");
        }
        return ServerResponse.createBySuccess(list);
    }


}
