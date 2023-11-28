package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录接口
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("登录接口")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        Employee employee = employeeService.login(employeeLoginDTO);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),//秘钥
                jwtProperties.getAdminTtl(),//过期时间
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("登出")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增员工
     *
     * @param employeeDTO
     * @return
     */

    @PostMapping
    @ApiOperation("新增员工接口")
    public Result<?> save(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * 分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询接口")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        PageResult page = employeeService.page(employeePageQueryDTO);
        return Result.success(page);
    }

    /**
     * 修改账号状态
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("修改账号状态")
    public Result<String> SetStatus(@PathVariable Integer status, Long id) {
        employeeService.setStatus(status, id);
        return Result.success();

    }

    /**
     * 修改员工基本信息
     *
     * @param employeeDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改员工信息")
    public Result<String> updateEntity(EmployeeDTO employeeDTO) {
        employeeService.updateEntity(employeeDTO);
        return Result.success();
    }

    /**
     * 修改账号密码
     * @param passwordEditDTO
     * @return
     */
    @PutMapping("/editPassword")
    @ApiOperation("修改账号密码")
    public Result<String> updatePassword(PasswordEditDTO passwordEditDTO) {
        employeeService.updatePassword(passwordEditDTO);
        return Result.success();
    }

    /**
     * 上传用户头像
     * @param file
     * @return
     */
    @PostMapping("logo")
    @ApiOperation("自定义的上传头像接口")
    public Result<String> saveLogo(@RequestBody MultipartFile file) {
        //上传头像
        employeeService.saveLogo(file);
        return Result.success();
    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @GetMapping("/admin/employee/{id}")
    @ApiOperation("根据id查询员工")
    public Result<?> selectEmployee(@PathVariable long id) {
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return Result.success(employee);
        } else {
            return Result.error("没有这名员工");
        }
    }


}
