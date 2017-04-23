package cn.exi.test;

import cn.exi.model.Customer;
import cn.exi.service.CustomerService;
import cn.exi.service.impl.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangHailiang on 2017/4/23.
 */
public class CustomerTest {
    private final CustomerService customerService ;

    public CustomerTest(){
        customerService = new CustomerServiceImpl();
    }

    @Before
    public void init(){
        //TODO 初始化数据库
        //DatabaseHelper.executeSQLFile("sql/customer_init.sql");
    }

    @Test
    public void getCustomerListTest() throws Exception{
        List<Customer> customerList = customerService.getCustomerList("cu");
        System.out.println("size>>>>>"+customerList.size());
    }

    @Test
    public void getCustomerTest() throws Exception{
        Customer customer = customerService.getCustomerById(1);
        System.out.println(customer.toString());
    }

    @Test
    public void updateCUstomerTest() throws Exception{
        long id = 1;
        Map<String,Object> fieldMap = new HashMap<>();
        fieldMap.put("contact","Harley");
        boolean result = customerService.updateCustomer(id, fieldMap);
        Assert.assertTrue(result);
    }

}
