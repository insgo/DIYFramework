package cn.exi.service.impl;

import cn.exi.model.Customer;
import cn.exi.service.CustomerService;

import java.util.List;
import java.util.Map;

/**
 * Created by HuangHailiang on 2017/4/23.
 */
public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<Customer> getCustomerList(String keywork) throws Exception {
        //TODO
        return null;
    }

    @Override
    public Customer getCustomerById(long id) throws Exception {
        return null;
    }

    @Override
    public boolean createCustomer(Map<String, Object> fieldMap) throws Exception {
        return false;
    }

    @Override
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) throws Exception {
        return false;
    }

    @Override
    public boolean deleteCustomer(long id) throws Exception {
        return false;
    }

}
