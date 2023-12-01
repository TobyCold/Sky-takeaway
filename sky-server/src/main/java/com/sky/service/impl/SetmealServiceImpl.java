package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    SetmealMapper setmealMapper;
    @Autowired
    SetmealDishMapper setmealDishMapper;

    /**
     * 新增套餐
     *
     * @param setmealDTO
     */

    @Transactional
    public void saveSetmeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.saveSetmeal(setmeal);
        Setmeal toSetmeal = Setmeal.builder()
                .name(setmealDTO.getName())
                .build();

        for (SetmealDish setmealDish : setmealDTO.getSetmealDishes()) {
            setmealDish.setSetmealId(setmealMapper.selectStemeal(toSetmeal).get(0).getId());
            setmealDishMapper.saveSetmealDishs(setmealDish);
        }
    }

    /**
     * 修改套餐
     *
     * @param setmealDTO
     */
    public void updateSetmeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = Setmeal.builder()
                .build();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.upDateSetmeal(setmeal);
    }

    /**
     * 根据Id查询套餐
     *
     * @param id
     * @return Integer
     */
    public SetmealDTO getById(long id) {
        Setmeal setmeal = Setmeal.builder()
                .id(id)
                .build();
        Setmeal toSetmeal = setmealMapper.selectStemeal(setmeal).get(0);

        SetmealDish setmealId = SetmealDish.builder()
                .setmealId(id)
                .build();
        List<SetmealDish> select = setmealDishMapper.select(setmealId);

        SetmealDTO toSetmealDTO = SetmealDTO.builder()
                .setmealDishes(select)
                .build();

        BeanUtils.copyProperties(toSetmeal, toSetmealDTO);
        return toSetmealDTO;
    }

    /**
     * 套餐分页查询
     *
     * @param setmealPageQueryDTO
     * @return
     */
    public PageResult getPage(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());

        Setmeal setmeal = Setmeal.builder().build();
        BeanUtils.copyProperties(setmealPageQueryDTO, setmeal);
        Page<Setmeal> page = setmealMapper.page(setmeal);
        long total = page.getTotal();


        return new PageResult(total, page);
    }

    /**
     * 根据id删除套餐
     *
     * @param ids
     */
    @Transactional
    public void deleteSetmeal(long[] ids) {
        setmealMapper.deleteSetmeal(ids);
        setmealDishMapper.deleteSetmealDishs(ids);
    }
}
