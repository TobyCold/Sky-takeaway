package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class EmployeeTest {

    @Autowired
    EmployeeService employeeService;
    @Test
    public void selectEmployee(){
        Employee byId = employeeService.getById(1);
        log.info("查询结果为:{}", byId);
    }

    @Test
    public void saveEmployeeTest(){
        EmployeeDTO build = EmployeeDTO.builder()
                .sex("男")
                .phone("16607924229")
                .name("Cold")
                .idNumber("123456789098765123")
                .username("123456789")
                .build();
        employeeService.save(build);
    }

    @Test
    public void pageTest(){
        EmployeePageQueryDTO employeePageQueryDTO = EmployeePageQueryDTO.builder()
                .page(1)
                .pageSize(3)
                .build();
        PageResult page = employeeService.page(employeePageQueryDTO);
        log.info("total:{}, list:[{}]", page.getTotal(), page.getRecords().toArray());
    }

    @Test
    public void updateEmployeeTest(){
        EmployeeDTO build = EmployeeDTO.builder()
                .sex("1")
                .id(3L)
                .build();
        employeeService.updateEntity(build);
    }

    @Test
    public void selectEmployeeById(){
        Employee byId = employeeService.getById(1L);
        log.info("Employee:{}", byId);
    }
}
