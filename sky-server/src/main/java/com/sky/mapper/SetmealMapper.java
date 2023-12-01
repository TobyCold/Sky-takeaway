package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 修改套餐
     * TODO 这里AOP填充不了时间，后期修复吧，还有为什么status不能进行空串判断
     * @param setmeal
     */
    @AutoFill(value = OperationType.UPDATE)
    void upDateSetmeal(Setmeal setmeal);


    /**
     * 新增套餐
     * @param setmeal
     */
    @AutoFill(value = OperationType.INSERT)
    void saveSetmeal(Setmeal setmeal);

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    /**
     * 分页查询
     * @param setmeal
     * @return
     */
    Page<Setmeal> page(Setmeal setmeal);


    /**
     * 批量删除套餐
     * @param ids
     */
    void deleteSetmeal(long[] ids);

    /**
     * 查询套餐
     * @param setmeal
     * @return
     */
    List<Setmeal> selectStemeal(Setmeal setmeal);
}
