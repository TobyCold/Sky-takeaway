package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * TODO 这里注意一下，防止SQL注入，项目完结时，研究下，统一账号验证时间
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 添加员工
     * @param employee
     */

    @Insert("insert into employee (name, username, password, phone, sex, id_number, status, create_time, update_time, create_user,update_user)" +
            "values" +
            "(#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Employee employee);

    /**
     * 根据id查询员工
     * @param id
     * @return
     * FIXME 这个方法业务层还没使用，后期用不到，删除！
     */
    @Select("select * from employee where id = #{id}")
    Employee getById(Long id);

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用/禁用 员工账号
     * @param status
     * @param id
     * FIXME 这里可以写个动态sql 待优化！
     */
    @Update("update employee set status = #{status} where id = #{id}")
    void setStatus(int status, long id);

    /**
     * 修改账号密码
     * @param employee
     */
    void updateEntity(Employee employee);
}
