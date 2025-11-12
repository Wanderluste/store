package com.cy.store.entity;

import java.util.Objects;

public class Favorites extends BaseEntity {
    private Integer fid;
    private Integer uid;
    private Integer pid;
    private String image;
    private Long price;
    private String title;
    private String sellPoint;
    private Integer status;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Favorites favorites = (Favorites) o;
        return Objects.equals(getFid(), favorites.getFid()) && Objects.equals(getUid(), favorites.getUid()) && Objects.equals(getPid(), favorites.getPid()) && Objects.equals(getImage(), favorites.getImage()) && Objects.equals(getPrice(), favorites.getPrice()) && Objects.equals(getTitle(), favorites.getTitle()) && Objects.equals(getSellPoint(), favorites.getSellPoint()) && Objects.equals(getStatus(), favorites.getStatus());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(getFid());
        result = 31 * result + Objects.hashCode(getUid());
        result = 31 * result + Objects.hashCode(getPid());
        result = 31 * result + Objects.hashCode(getImage());
        result = 31 * result + Objects.hashCode(getPrice());
        result = 31 * result + Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getSellPoint());
        result = 31 * result + Objects.hashCode(getStatus());
        return result;
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "fid=" + fid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", status=" + status +
                '}';
    }
}
