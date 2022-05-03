package mapper;


import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ssm.entity.Department;
import ssm.entity.Employee;
import ssm.mapper.DepartmentMapper;
import ssm.mapper.EmployeeMapper;

import java.util.UUID;

/**
 * @Description TODO
 * @Author 0X27
 * @Date 2022年4月26日 下午9:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    /**
     * 测试DepartmentMapper
     */
    @Test
    public void testCRUD(){
        System.out.println(departmentMapper);

//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));
//
//        employeeMapper.insertSelective(new Employee(null,"admin","男","123@gmail.com",1));
//        employeeMapper.insertSelective(new Employee(null,"张三","男","123@qq.com",2));
//        employeeMapper.insertSelective(new Employee(null,"李四","女","123@163.com",2));

//        批量插入多个员工：使用sqlSession
        employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 100; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 8) + i;
            employeeMapper.insertSelective(new Employee(null,uid,"男","123@gmail.com",1));
        }

    }

}
