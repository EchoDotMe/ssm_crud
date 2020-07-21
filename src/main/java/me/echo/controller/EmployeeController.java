package me.echo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.echo.bean.Employee;
import me.echo.bean.Msg;
import me.echo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理员工CRUD请求
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

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
