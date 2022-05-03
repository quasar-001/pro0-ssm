package ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.entity.Department;
import ssm.mapper.DepartmentMapper;

import java.util.List;

/**
 * @Description TODO
 * @Author 0X27
 * @Date 2022年4月30日 下午4:25
 */
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * @Author 0X27
     * @Description 返回所有部门信息
     * @Date 下午4:25 2022年4月30日
     * @Param []
     * @return java.util.List<ssm.entity.Department>
     */
    public List<Department> getDepts() {
        return departmentMapper.selectByExample(null);
    }

}
