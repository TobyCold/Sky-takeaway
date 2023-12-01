package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
public class DishController {

    @Autowired
    DishService dishService;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    Result<?> page(DishPageQueryDTO dishPageQueryDTO){
        PageResult page = dishService.page(dishPageQueryDTO);
        return Result.success(page);
    }

    @PostMapping
    @ApiOperation("增加菜品")
    Result<?> save(@RequestBody DishDTO dishDTO){
        dishService.save(dishDTO);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("修改菜品")
    Result<?> update(@RequestBody DishDTO dishDTO){
        dishService.update(dishDTO);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("批量删除菜品")
    Result<?> DeleteDish(@RequestParam("ids") Long[] ids){
        dishService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<?> getById(@PathVariable Long id){
        return Result.success(dishService.getById(id));
    }

    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<?> getByCategoryId(Long categoryId){
        return Result.success(dishService.getByCategoryId(categoryId));
    }

    @PostMapping("/status/{status}")
    @ApiOperation("修改菜品状态")
    public Result<?> setStatus(@PathVariable Integer status, Long id){
        DishDTO dishDTO = DishDTO.builder()
                .status(status)
                .id(id)
                .build();
        dishService.update(dishDTO);
        return Result.success();
    }
}
