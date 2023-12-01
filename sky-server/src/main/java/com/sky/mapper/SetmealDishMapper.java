package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 查询套餐菜品
     * @param setmealDish
     * @return
     */

    List<SetmealDish> select(SetmealDish setmealDish);

    /**
     * 保存套餐菜品
     * @param setmealDish
     */
    void saveSetmealDishs(SetmealDish setmealDish);

    void deleteSetmealDishs(long[] ids);
}
