package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("AdminShop")
@RequestMapping("admin/shop")
@Api(tags = "店铺相关接口")
public class ShopController {

    @Autowired
    ShopService shopService;

    @GetMapping("status")
    @ApiOperation("获得店铺状态")
    public Result<?> getStatus(){
        return Result.success(shopService.getStatus());
    }

    @PutMapping("{status}")
    @ApiOperation("设置店铺状态")
    public Result setStatus(@PathVariable Integer status){
        shopService.setStatus(status);
        return Result.success();
    }
}
