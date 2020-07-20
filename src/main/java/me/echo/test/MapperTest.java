package me.echo.test;

import me.echo.bean.Department;
import me.echo.bean.Employee;
import me.echo.dao.DepartmentMapper;
import me.echo.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SqlSession sqlSession;

    /**
     * 测试 department
     */
    @Test
    public void testCRUD(){
        Department department = departmentMapper.selectByPrimaryKey(2);
        System.out.println(department);

//        int i = employeeMapper.insertSelective(new Employee(null, "echo", "M", "echo.me@hotmail.com", 1));
//        System.out.println(i);

        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(4015);
        System.out.println(employee);

//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        for (int i = 0; i<1000; i++){
//            String uid = UUID.randomUUID().toString().substring(0, 5)+i;
//
//        }
    }
}
