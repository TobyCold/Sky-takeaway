package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;

import java.util.List;

public interface DishService {
    /**
     * 分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 新增菜品
     * @param dishDTO
     */
    void save(DishDTO dishDTO);

    /**
     * 修改菜品
     * @param dishDTO
     */
    void update(DishDTO dishDTO);

    /**
     * 批量删除菜品
     * @param ids
     */
    void delete(Long[] ids);

    Dish getById(Long id);

    List<Dish> getByCategoryId(Long categoryId);
}
