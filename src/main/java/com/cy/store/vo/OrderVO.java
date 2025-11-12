package com.cy.store.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class OrderVO implements Serializable {
    private Integer oid;
    private Integer aid;
    private String recvName;
    private String zip;
    private String phone;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String address;
    private Long totalPrice;
    private Integer status;
    private Date orderTime;
    private Date payTime;
    private String image;
    private String title;
    private Long price;
    private Integer num;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderVO orderVO = (OrderVO) o;
        return Objects.equals(getOid(), orderVO.getOid()) && Objects.equals(getAid(), orderVO.getAid()) && Objects.equals(getRecvName(), orderVO.getRecvName()) && Objects.equals(getZip(), orderVO.getZip()) && Objects.equals(getPhone(), orderVO.getPhone()) && Objects.equals(getProvinceName(), orderVO.getProvinceName()) && Objects.equals(getCityName(), orderVO.getCityName()) && Objects.equals(getAreaName(), orderVO.getAreaName()) && Objects.equals(getAddress(), orderVO.getAddress()) && Objects.equals(getTotalPrice(), orderVO.getTotalPrice()) && Objects.equals(getStatus(), orderVO.getStatus()) && Objects.equals(getOrderTime(), orderVO.getOrderTime()) && Objects.equals(getPayTime(), orderVO.getPayTime()) && Objects.equals(getImage(), orderVO.getImage()) && Objects.equals(getTitle(), orderVO.getTitle()) && Objects.equals(getPrice(), orderVO.getPrice()) && Objects.equals(getNum(), orderVO.getNum());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getOid());
        result = 31 * result + Objects.hashCode(getAid());
        result = 31 * result + Objects.hashCode(getRecvName());
        result = 31 * result + Objects.hashCode(getZip());
        result = 31 * result + Objects.hashCode(getPhone());
        result = 31 * result + Objects.hashCode(getProvinceName());
        result = 31 * result + Objects.hashCode(getCityName());
        result = 31 * result + Objects.hashCode(getAreaName());
        result = 31 * result + Objects.hashCode(getAddress());
        result = 31 * result + Objects.hashCode(getTotalPrice());
        result = 31 * result + Objects.hashCode(getStatus());
        result = 31 * result + Objects.hashCode(getOrderTime());
        result = 31 * result + Objects.hashCode(getPayTime());
        result = 31 * result + Objects.hashCode(getImage());
        result = 31 * result + Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getPrice());
        result = 31 * result + Objects.hashCode(getNum());
        return result;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "oid=" + oid +
                ", aid=" + aid +
                ", recvName='" + recvName + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", address='" + address + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", orderTime=" + orderTime +
                ", payTime=" + payTime +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }
}
