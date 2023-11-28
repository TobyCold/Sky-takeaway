package com.sky.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = DigestUtil.md5Hex(employeeLoginDTO.getPassword());
        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 新增员工
     *
     * @param employeeDTO
     */
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        //设置默认密码/默认账号启用
        employee.setPassword(DigestUtil.md5Hex(PasswordConstant.DEFAULT_PASSWORD));
        employee.setStatus(StatusConstant.ENABLE);

        //设置修改和创建的时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        //操作创建的用户
        //这里要拿到验证JWT时存储在线程空间的值
        Long createId = BaseContext.getCurrentId();
        employee.setCreateUser(createId);
        employee.setUpdateUser(createId);

        employeeMapper.insert(employee);
    }

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    public PageResult page(EmployeePageQueryDTO employeePageQueryDTO) {
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.page(employeePageQueryDTO);
        long total = page.getTotal();
        List<Employee> result = page.getResult();
        return new PageResult(total, result);
    }

    /**
     * 启用/禁用 员工账号
     * @param status
     * @param id
     */
    public void setStatus(int status, long id) {
        employeeMapper.setStatus(status, id);
    }

    /**
     * 修改员工
     * @param employeeDTO
     */
    public void updateEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employeeMapper.updateEntity(employee);
    }

    /**
     * 修改账号密码
     * @param passwordEditDTO
     */
    public void updatePassword(PasswordEditDTO passwordEditDTO) {
        Employee employee = employeeMapper.getById(passwordEditDTO.getEmpId());
        if (!employee.getPassword().equals(passwordEditDTO.getOldPassword())) {
            throw new PasswordErrorException();
        }
        employee.setPassword(passwordEditDTO.getNewPassword());
        employeeMapper.updateEntity(employee);
    }

    /**
     * 根据id查询员工
     * @return
     */
    public Employee getById(long id) {
       return employeeMapper.getById(id);
    }

    /**
     * 上传用户头像
     * @param file
     */
    public void saveLogo(MultipartFile file) {

    }
}
