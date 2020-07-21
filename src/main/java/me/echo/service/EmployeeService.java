package me.echo.service;

import me.echo.bean.Employee;
import me.echo.bean.EmployeeExample;
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

    /**
     * 保存员工
     */
    public void saveEmp(Employee employee){
        employeeMapper.insertSelective(employee);
    }

    /**
     * 检测用户名是否可用
     * @param empName 传入的用户名
     * @return
     */
    public Boolean checkUser(String empName){
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long countByExample = employeeMapper.countByExample(employeeExample);
        return countByExample == 0;
    }
}
