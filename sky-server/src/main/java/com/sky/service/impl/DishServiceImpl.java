package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishMapper dishMapper;

    /**
     * 分页查询
     *
     * @param dishPageQueryDTO
     * @return PageResult
     */
    public PageResult page(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishPageQueryDTO, dish);
        Page<Dish> dishes = dishMapper.page(dish);
        long total = dishes.getTotal();
        return new PageResult(total, dishes);
    }

    /**
     * 新增菜品
     *
     * @param dishDTO
     */
    @AutoFill(OperationType.INSERT)
    public void save(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.save(dish);
    }

    /**
     * 修改菜品
     *
     * @param dishDTO
     */
    @AutoFill(OperationType.UPDATE)
    public void update(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        System.out.println(dish.getStatus());
        dishMapper.update(dish);
    }

    /**
     * 批量删除菜品
     *
     * @param ids
     */
    public void delete(Long[] ids) {
        dishMapper.deleteById(ids);
    }

    /**
     * 根据Id查询菜品 (1对1)
     *
     * @param id
     * @return
     */
    public Dish getById(Long id) {
        Dish build = Dish.builder()
                .id(id)
                .build();

        //这里是根据主键查询，必然只有一条数据
        List<Dish> dish = dishMapper.getDish(build);
        return dish.get(0);
    }

    /**
     * 根据菜品分类id查询菜品 (1对多)
     * @param categoryId
     * @return
     */
    public List<Dish> getByCategoryId(Long categoryId) {
        Dish build = Dish.builder()
                .categoryId(categoryId)
                .build();
        return dishMapper.getDish(build);
    }
}
