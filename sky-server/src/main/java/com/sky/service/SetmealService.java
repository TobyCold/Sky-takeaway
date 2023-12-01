package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;

public interface SetmealService {
    /**
     * 新增套餐
     * @param setmealDTO
     */

    void saveSetmeal(SetmealDTO setmealDTO);

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void updateSetmeal(SetmealDTO setmealDTO);

    /**
     * 根据Id查询套餐
     *
     * @param id
     * @return Setmeal对象
     */
    SetmealDTO getById(long id);

    /**
     * 分页查询套餐
     * @param setmealPageQueryDTO
     * @return
     */

    PageResult getPage(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 根据id删除套餐
     * @param ids
     */
    void deleteSetmeal(long[] ids);
}
