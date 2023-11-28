package com.sky.mapper;

import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface set {

    /**
     * 修改套餐
     * @param setmeal
     */
    void upDateSetmeal(Setmeal setmeal);

    /**
     * 新增套餐
     * @param setmeal
     */
    void saveSetmeal(Setmeal setmeal);

    /**
     * 根据Id查询套餐
     * @param id
     * @return Setmeal对象
     */
    @Select("select * from setmeal where id = #{id}")
    Setmeal getById(long id);

    List<Setmeal> getPage();

    /**
     * 存储套餐对应的菜品
     * 与 saveSetmeal 方法对应使用
     * @param setmealDish
     */
    void saveSetmealDishs(SetmealDish setmealDish);
}
