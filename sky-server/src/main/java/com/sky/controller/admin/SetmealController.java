package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/admin/setmeal")
@Api("套餐相关接口")
public class SetmealController {

    /**
     * 修改套餐
     * @return
     */
    @PutMapping
    @ApiOperation("修改套餐")
    public Result<?> updateSetmeal(@RequestBody SetmealDTO setmealDTO){
        return null;
    }

    /**
     * 增加套餐
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("增加套餐")
    public Result<?> addSetmeal(@RequestBody SetmealDTO setmealDTO){
        return null;
    }

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    @ApiOperation("根据id查询套餐")
    @GetMapping("{id}")
    public Result<?> getSetmealById(@PathVariable long id){
        return null;
    }

    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @ApiOperation("删除套餐")
    @DeleteMapping
    public Result<?> DeleteSetmeal(long ids){
        return null;
    }

}
