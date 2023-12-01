package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@ResponseBody
@RequestMapping("/admin/setmeal")
@Api("套餐相关接口")
public class SetmealController {
    @Autowired
    SetmealService setmealService;

    /**
     * 修改套餐
     * @return
     */
    @PutMapping
    @ApiOperation("修改套餐")
    public Result<?> updateSetmeal(@RequestBody SetmealDTO setmealDTO){
        setmealService.updateSetmeal(setmealDTO);
        return Result.success();
    }

    /**
     * 增加套餐
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增套餐")
    public Result<?> addSetmeal(@RequestBody SetmealDTO setmealDTO){
        setmealService.saveSetmeal(setmealDTO);
        return Result.success();
    }

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("根据id查询套餐")
    public Result<?> getSetmealById(@PathVariable long id){
        SetmealDTO setmealDTO = setmealService.getById(id);
        return Result.success(setmealDTO);
    }

    /**
     * 批量删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("删除套餐")
    public Result<?> deleteSetmeal(long[] ids){
        setmealService.deleteSetmeal(ids);
        return null;
    }

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("page")
    @ApiOperation("分页查询")
    public Result<PageResult> getPage(SetmealPageQueryDTO setmealPageQueryDTO){
        PageResult page = setmealService.getPage(setmealPageQueryDTO);
        return Result.success(page);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("修改套餐状态")
    public Result<?> setStatus(long id,@PathVariable Integer status){
        SetmealDTO setmealDTO = SetmealDTO.builder()
                .id(id)
                .status(status)
                .build();
        setmealService.updateSetmeal(setmealDTO);
        return Result.success();
    }


}
