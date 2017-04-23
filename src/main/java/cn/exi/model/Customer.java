package cn.exi.model;

/**
 * Created by HuangHailiang on 2017/4/23.
 */
public class Customer {

    private long id; //用户ID
    private String name; //用户名
    private String contact; //联系地址
    private String telephone; //电话号码
    private String email; //邮件地址
    private String remark;//备注

    public Customer(String name, String contact, String telephone, String email, String remark) {
        this.name = name;
        this.contact = contact;
        this.telephone = telephone;
        this.email = email;
        this.remark = remark;
    }

    public Customer(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
