package cn.exi.service;

import cn.exi.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * Created by HuangHailiang on 2017/4/23.
 */
public interface CustomerService  {
    /**
     * 获取客户列表
     * @param keywork 关键字
     * @return
     * @throws Exception
     */
    public List<Customer> getCustomerList(String keywork) throws Exception;

    /**
     * 根据ID获取客户信息
     * @param id 客户ID
     * @return
     * @throws Exception
     */
    public Customer getCustomerById(long id) throws Exception;

    /**
     *  创建客户
     * @param fieldMap 客户信息字段map
     * @return
     * @throws Exception
     */
    public boolean createCustomer(Map<String ,Object> fieldMap) throws Exception;

    /**
     * 根据ID更新客户信息
     * @param id 客户ID
     * @param fieldMap 客户信息字段map
     * @return
     * @throws Exception
     */
    public boolean updateCustomer(long id,Map<String,Object> fieldMap) throws Exception;

    /**
     * 删除客户
     * @param id 客户ID
     * @return
     * @throws Exception
     */
    public boolean deleteCustomer(long id ) throws Exception;
}
