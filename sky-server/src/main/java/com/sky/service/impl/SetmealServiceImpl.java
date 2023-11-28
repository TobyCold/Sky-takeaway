package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.set;
import com.sky.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    set set;

    /**
     * 新增套餐
     * TODO 已完成待测试
     * @param setmealDTO
     */

    @Transactional
    public void saveSetmeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmeal.setCreateTime(LocalDateTime.now());
        setmeal.setCreateUser(BaseContext.getCurrentId());
        setmeal.setUpdateTime(LocalDateTime.now());
        setmeal.setUpdateUser(BaseContext.getCurrentId());
        set.saveSetmeal(setmeal);
        SetmealDish setmealDish = new SetmealDish();
        BeanUtils.copyProperties(setmealDTO.getSetmealDishes(), setmealDish);
        set.saveSetmealDishs(setmealDish);
    }

    /**
     * 修改套餐
     * TODO 已完成待测试
     * @param setmeal
     */
    public void updateSetmeal(Setmeal setmeal) {
        set.upDateSetmeal(setmeal);
    }

    /**
     * 根据Id查询套餐
     * TODO 已完成待测试
     * @param id
     * @return Setmeal对象
     */
    public Setmeal getById(long id) {
       return set.getById(id);
    }
}
