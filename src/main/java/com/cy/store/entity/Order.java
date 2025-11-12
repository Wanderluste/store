package com.cy.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order extends BaseEntity implements Serializable {
    private Integer oid;
    private Integer uid;
    private Integer aid;
    private String recvName;
    private String recvPhone;
    private String recvProvince;
    private String recvCity;
    private String recvArea;
    private String recvAddress;
    private Long totalPrice;
    private Integer status;
    private Date orderTime;
    private Date payTime;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    public String getRecvPhone() {
        return recvPhone;
    }

    public void setRecvPhone(String recvPhone) {
        this.recvPhone = recvPhone;
    }

    public String getRecvProvince() {
        return recvProvince;
    }

    public void setRecvProvince(String recvProvince) {
        this.recvProvince = recvProvince;
    }

    public String getRecvCity() {
        return recvCity;
    }

    public void setRecvCity(String recvCity) {
        this.recvCity = recvCity;
    }

    public String getRecvArea() {
        return recvArea;
    }

    public void setRecvArea(String recvArea) {
        this.recvArea = recvArea;
    }

    public String getRecvAddress() {
        return recvAddress;
    }

    public void setRecvAddress(String recvAddress) {
        this.recvAddress = recvAddress;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;
        return Objects.equals(getOid(), order.getOid()) && Objects.equals(getUid(), order.getUid()) && Objects.equals(getAid(), order.getAid()) && Objects.equals(getRecvName(), order.getRecvName()) && Objects.equals(getRecvPhone(), order.getRecvPhone()) && Objects.equals(getRecvProvince(), order.getRecvProvince()) && Objects.equals(getRecvCity(), order.getRecvCity()) && Objects.equals(getRecvArea(), order.getRecvArea()) && Objects.equals(getRecvAddress(), order.getRecvAddress()) && Objects.equals(getTotalPrice(), order.getTotalPrice()) && Objects.equals(getStatus(), order.getStatus()) && Objects.equals(getOrderTime(), order.getOrderTime()) && Objects.equals(getPayTime(), order.getPayTime());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(getOid());
        result = 31 * result + Objects.hashCode(getUid());
        result = 31 * result + Objects.hashCode(getAid());
        result = 31 * result + Objects.hashCode(getRecvName());
        result = 31 * result + Objects.hashCode(getRecvPhone());
        result = 31 * result + Objects.hashCode(getRecvProvince());
        result = 31 * result + Objects.hashCode(getRecvCity());
        result = 31 * result + Objects.hashCode(getRecvArea());
        result = 31 * result + Objects.hashCode(getRecvAddress());
        result = 31 * result + Objects.hashCode(getTotalPrice());
        result = 31 * result + Objects.hashCode(getStatus());
        result = 31 * result + Objects.hashCode(getOrderTime());
        result = 31 * result + Objects.hashCode(getPayTime());
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", aid=" + aid +
                ", recvName='" + recvName + '\'' +
                ", recvPhone='" + recvPhone + '\'' +
                ", recvProvince='" + recvProvince + '\'' +
                ", recvCity='" + recvCity + '\'' +
                ", recvArea='" + recvArea + '\'' +
                ", recvAddress='" + recvAddress + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", orderTime=" + orderTime +
                ", payTime=" + payTime +
                '}';
    }
}
