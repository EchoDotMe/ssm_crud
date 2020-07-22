package me.echo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.echo.bean.Employee;
import me.echo.bean.Msg;
import me.echo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理员工CRUD请求
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 查询员工信息
     * @param id 员工id
     */
    @ResponseBody
    @GetMapping("/emp/{id}")
    public Msg getEmp(@PathVariable Integer id){

       Employee employee = employeeService.getEmp(id);
       if (employee == null){
           return Msg.fail().add("reason", "查无此人!");
       }
        return Msg.success().add("emp", employee);
    }

    /**
     * 更新员工信息
     * @param employee 前端传来的employee
     * @return  操作结果消息
     *
     * 前端ajax直接 method:"PUT" 会报错
     * Employee{empId=8, empName='null', gender='null', email='null', dId=null, department=null}
     * Tomcat 默认只对POST请求封装对象，所以除路径变量外，其余的属性全部为空引发sql异常
     *
     * Spring mvc 提供了解决方案：org.springframework.web.filter.HttpPutFormContentFilter
     * 配置HttpPutFormContentFilter过滤器即可
     * 作用：
     *  将请求体中的数据解析包装成一个map，request被重新包装，重写了request.getParameter()方法，就会从自己封装的map中取数据
     *
     */
    @ResponseBody
    @PutMapping("/emp/{empId}")
    public Msg updateEmp(Employee employee){
        // 校验数据
//        System.out.println(employee);
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /**
     * 删除员工（单个， 批量二合一）
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("/emp/{ids}")
    public Msg deleteEmp(@PathVariable String ids){
        if (ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            for (String s : ids.split("-")) {
                del_ids.add(Integer.parseInt(s));
            }
//            System.out.println(del_ids);
            employeeService.deleteBatch(del_ids);
        }else {
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }

        return Msg.success();
    }

    /**
     * 员工保存
     */
    @ResponseBody
    @PostMapping("/emp")
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if (result.hasErrors()){
            Map<String, Object> map = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError:fieldErrors){
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFileds", map);
        }
//        System.out.println(employee);
        employeeService.saveEmp(employee);
        return Msg.success();
    }

    /**
     * 检测用户名是否可用
     * @param empName
     * @return
     */
    @ResponseBody
    @PostMapping("/checkUser")
    public Msg checkUser(@RequestParam("empName")String empName){
        // 判断用户名合法性
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!empName.matches(regx)){
           return new Msg(201, "用户名不可用, 需满足2-5位中文，或6-16位英文", null);
        }
        if (employeeService.checkUser(empName)){
            return Msg.success();
        }
        return new Msg(201, "用户名不可用", null);
    }

    /**
     * 改造getEmps方法 直接向客户端返回json数据
     * @param pn 页码
     * @param model
     * @return json数据
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model){
        PageHelper.startPage(pn, 10);
        // startPage后面这个查询就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        // 使用PageInfo包装查询后的结果，其中封装了详细信息, 传入连续显示的页数
        PageInfo<Employee> pages = new PageInfo<>(emps, 5);
        return Msg.success().add("pageInfo", pages);
    }


    /*
     * 查询员工数据
     */
//    @RequestMapping("/emps")
//    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model){
//        PageHelper.startPage(pn, 5);
//        // startPage后面这个查询就是一个分页查询
//        List<Employee> emps = employeeService.getAll();
//        // 使用PageInfo包装查询后的结果，其中封装了详细信息, 传入连续显示的页数
//        PageInfo page = new PageInfo(emps, 5);
//        model.addAttribute("pageInfo", page);
//        return "list";
//    }
}
