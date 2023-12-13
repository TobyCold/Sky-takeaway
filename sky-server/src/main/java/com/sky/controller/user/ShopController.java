package com.sky.controller.user;


import com.sky.result.Result;
import com.sky.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("UserShop")
@RequestMapping("user/shop")
@Api(tags = "C端-店铺相关接口")
public class ShopController {

    @Autowired
    ShopService shopService;

    @GetMapping("status")
    @ApiOperation("获得店铺状态")
    public Result<?> getStatus(){
        return Result.success(shopService.getStatus());
    }

}
