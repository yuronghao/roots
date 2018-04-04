package com.emi.roots.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AzOrder {
    private Integer id;

    private String order_id;

    private Integer uid;

    private String invoice;

    private String arrival_time;

    private BigDecimal total_money;

    private BigDecimal ship_fee;

    private Integer total_credit;

    private Integer coupon_id;

    private BigDecimal coupon_money;

    private Integer credit_use;

    private String receiver;

    private String province;

    private String city;

    private String district;

    private String address;

    private String post_code;

    private String tel;

    private Boolean status;

    private String order_ip;

    private Date pay_time;

    private Date delivery_time;

    private Date create_time;

    private Date update_time;

    private String express_cname;

    private String express_kid;

    private String payment_id;

    private String payway;

    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id == null ? null : order_id.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice == null ? null : invoice.trim();
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time == null ? null : arrival_time.trim();
    }

    public BigDecimal getTotal_money() {
        return total_money;
    }

    public void setTotal_money(BigDecimal total_money) {
        this.total_money = total_money;
    }

    public BigDecimal getShip_fee() {
        return ship_fee;
    }

    public void setShip_fee(BigDecimal ship_fee) {
        this.ship_fee = ship_fee;
    }

    public Integer getTotal_credit() {
        return total_credit;
    }

    public void setTotal_credit(Integer total_credit) {
        this.total_credit = total_credit;
    }

    public Integer getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Integer coupon_id) {
        this.coupon_id = coupon_id;
    }

    public BigDecimal getCoupon_money() {
        return coupon_money;
    }

    public void setCoupon_money(BigDecimal coupon_money) {
        this.coupon_money = coupon_money;
    }

    public Integer getCredit_use() {
        return credit_use;
    }

    public void setCredit_use(Integer credit_use) {
        this.credit_use = credit_use;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code == null ? null : post_code.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getOrder_ip() {
        return order_ip;
    }

    public void setOrder_ip(String order_ip) {
        this.order_ip = order_ip == null ? null : order_ip.trim();
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Date getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(Date delivery_time) {
        this.delivery_time = delivery_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getExpress_cname() {
        return express_cname;
    }

    public void setExpress_cname(String express_cname) {
        this.express_cname = express_cname == null ? null : express_cname.trim();
    }

    public String getExpress_kid() {
        return express_kid;
    }

    public void setExpress_kid(String express_kid) {
        this.express_kid = express_kid == null ? null : express_kid.trim();
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id == null ? null : payment_id.trim();
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway == null ? null : payway.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}