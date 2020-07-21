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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
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
        PageHelper.startPage(pn, 5);
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
