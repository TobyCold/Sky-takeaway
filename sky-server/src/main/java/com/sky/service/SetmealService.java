package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;

public interface SetmealService {
    /**
     * 新增套餐
     * @param setmealDTO
     */

    void saveSetmeal(SetmealDTO setmealDTO);

    /**
     * 修改套餐
     * @param setmeal
     */
    void updateSetmeal(Setmeal setmeal);

    /**
     * 根据Id查询套餐
     * @param id
     * @return Setmeal对象
     */
    Setmeal getById(long id);

}
