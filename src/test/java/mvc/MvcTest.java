package mvc;

import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ssm.entity.Employee;

import java.util.List;

/**
 * @Description TODO
 * @Author 0X27
 * @Date 2022年4月26日 下午10:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MvcTest {

    //Spirng mvc ioc容器
    @Autowired
    WebApplicationContext context;

    //虚拟mvc请求
    MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testMvc() throws Exception {
        //模拟请求拿到返回值
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/").param("pn","1")).andReturn();

        MockHttpServletRequest request = mvcResult.getRequest();
        PageInfo pageInfo =     (PageInfo) request.getAttribute("pageInfo");

        System.out.println("当前页码："+pageInfo.getPageNum());
        System.out.println("总页码："+pageInfo.getPages());
        System.out.println("总记录数："+pageInfo.getTotal());
        System.out.println("在页码连续显示的页码：");



        int[] nums = pageInfo.getNavigatepageNums();
        for (int num:nums){
            System.out.println(num);
        }

//        获取员工数据
        List<Employee> list = pageInfo.getList();
        for (Employee employee : list){
            System.out.println("ID："+employee.getEmpId()+"===>"+employee.getEmpName());
        }

    }

    @Test
    public void testGetDepName() throws Exception {
        //模拟请求拿到返回值
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getDepartment")).andReturn();

//        MockHttpServletRequest request = mvcResult.getRequest();

        MockHttpServletResponse response = mvcResult.getResponse();



        System.out.println( response.getContentAsString());

    }

}
