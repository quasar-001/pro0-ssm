package ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.entity.Employee;
import ssm.entity.EmployeeExample;
import ssm.mapper.EmployeeMapper;

import java.util.List;

/**
 * @Description TODO
 * @Author 0X27
 * @Date 2022年4月26日 下午10:29
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * @Author 0X27
     * @Description 获取所有员工(带部门信息)
     * @Date 下午5:00 2022年5月1日 
     * @Param []
     * @return java.util.List<ssm.entity.Employee>
     */
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }
    
    /**
     * @Author 0X27
     * @Description 添加员工
     * @Date 下午5:00 2022年5月1日 
     * @Param [employee]
     * @return int
     */
    public int save(Employee employee) {
        int i = employeeMapper.insertSelective(employee);
        return i;
    }

    /**
     * @Author 0X27
     * @Description 通过id删除员工
     * @Date 下午9:00 2022年5月1日
     * @Param [empId]
     * @return void
     */
    public void deleteEmpByEmpId(Integer empId){
        employeeMapper.deleteByPrimaryKey(empId);
    }

    /**
     * @Author 0X27
     * @Description 验证员工是否已存在，如果返回1，则不存在，可以添加员工，返回0则相反
     * @Date 下午2:57 2022年5月2日
     * @Param [username]
     * @return int
     */
    public int VerifyUsername(String username){

        EmployeeExample example = new EmployeeExample();

        example.createCriteria().andEmpNameEqualTo(username);

        List<Employee> employees = employeeMapper.selectByExample(example);

        boolean isEqual = false;

        for(Employee employee : employees){
            boolean equals = employee.getEmpName().equals(username);
            if (equals){
                isEqual = true;
            }else {
               isEqual = false;
            }
        }

        if(isEqual){
            return 0;
        }else{
            return 1;
        }

    }

    /**
     * @Author 0X27
     * @Description 修改员工数据
     * @Date 下午10:36 2022年5月2日
     * @Param [employee]
     * @return void
     */
    public int updateEmp(Employee employee){

        int update = employeeMapper.updateByPrimaryKey(employee);

        System.out.println(update);

        return update;
    }
    
}
