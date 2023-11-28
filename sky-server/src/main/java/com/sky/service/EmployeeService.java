package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 员工存储
     * @return
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用/禁用 员工账号
     * @param status
     * @param id
     */
    void setStatus(int status, long id);

    /**
     * 编辑员工
     * @param employeeDTO
     */
    void updateEntity(EmployeeDTO employeeDTO);

    /**
     * 修改账号密码
     * FIXME Dao同上，无需测试
     * @param passwordEditDTO
     */
    void updatePassword(PasswordEditDTO passwordEditDTO);

    /**
     * 根据id查询员工
     * FIXME 单元测试合格
     * @param id
     * @return
     */
    Employee getById(long id);

    void saveLogo(MultipartFile file);
}
