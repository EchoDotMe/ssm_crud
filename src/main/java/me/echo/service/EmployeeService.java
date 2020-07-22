package me.echo.service;

import me.echo.bean.Employee;
import me.echo.bean.EmployeeExample;
import me.echo.dao.EmployeeMapper;
import org.apache.taglibs.standard.lang.jstl.EmptyOperator;
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

    /**
     * 根据id获取员工信息
     */
    public Employee getEmp(Integer id){
        return employeeMapper.selectByPrimaryKeyWithDept(id);
    }

    /**
     * 更新用户信息
     */
    public void updateEmp(Employee employee){
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 删除单个用户信息
     */
    public void deleteEmp(Integer id){
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除用户
     * @param ids 包含用户id的整型数组 : [1, 2, 3]
     */
    public void deleteBatch(List<Integer> ids){
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }
}
