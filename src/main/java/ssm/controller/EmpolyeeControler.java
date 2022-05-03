package ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ssm.entity.Department;
import ssm.entity.Employee;
import ssm.entity.Msg;
import ssm.mapper.DepartmentMapper;
import ssm.service.DepartmentService;
import ssm.service.EmployeeService;

import java.util.List;

/**
 * @Description 处理员工的crud请求
 * @Author 0X27
 * @Date 2022年4月26日 下午10:25
 */
@Controller
public class EmpolyeeControler {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * @Author 0X27
     * @Description 以josn的形式返回给客户端，实现客户端的无关系
     * @Date 下午7:02 2022年4月28日 
     * @Param [pn, model]
     * @return com.github.pagehelper.PageInfo
     */
    @GetMapping("/emps")
    @ResponseBody
    public Msg getEmpWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn){
        //开始页码和每页的记录数
        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();
        PageInfo pageInfo = new PageInfo(emps,5);

        return Msg.success().add("pageInfo",pageInfo);
    }

//    @GetMapping(value = "/")
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {

        //开始页码和每页的记录数
        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();
        PageInfo pageInfo = new PageInfo(emps,5);
        model.addAttribute("pageInfo",pageInfo);
        return "views/list";
    }
    /**
     * @Author 0X27
     * @Description 获取所有部门信息
     * @Date 下午4:55 2022年5月1日
     * @Param []
     * @return ssm.entity.Msg
     */
    @GetMapping(value = "/getDepartment")
    @ResponseBody
    public Msg getDepartment(){

        //查询所有部门
        List<Department> departmentList = departmentService.getDepts();


        return Msg.success().add("departmentList",departmentList);
    }

    /**
     * @Author 0X27
     * @Description 添加员工
     * @Date 下午3:07 2022年5月2日
     * @Param [employee]
     * @return ssm.entity.Msg
     */
    @RequestMapping(
            value = "/saveEmp",
            method = RequestMethod.POST
    )
    @ResponseBody
    public Msg saveEmp(@RequestBody Employee employee){

        //添加@RequestBody注解，解决前端的vue的post的请求的content-type：application/json编码问题

       employeeService.save(employee);

        return Msg.success();
    }


    //删除员工
    @DeleteMapping("/deleteEmps/{empId}")
    @ResponseBody
    public Msg deleteEmployeeById(@PathVariable("empId") Integer empId){

       employeeService.deleteEmpByEmpId(empId);

        return Msg.success();
    }

    //验证用户名是否可用
    @GetMapping("/verifyEmpName")
    @ResponseBody
    public Msg verifyEmpName(String empName){

        int i = employeeService.VerifyUsername(empName);

        if(i == 1){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }

    @PutMapping("/updateEmp")
    @ResponseBody
    public Msg updateEmp(Employee employee){

        System.out.println(employee.toString());

        int result = employeeService.updateEmp(employee);

        System.out.println(result+"****************");

        return Msg.success();
    }

}
