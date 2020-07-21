package me.echo.service;

import me.echo.bean.Employee;
import me.echo.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     * @return 员工的集合
     */
    public List<Employee> getAll(){
        return employeeMapper.selectByExampleWithDept(null);
    }
}
