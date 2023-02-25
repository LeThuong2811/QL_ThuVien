/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class TheThuVien {

    private String soThe, ghiChu, ngayBatDau, ngayKetThuc;

    public String getSoThe() {
        return soThe;
    }

    public void setSoThe(String soThe) {
        this.soThe = soThe;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public TheThuVien() {
        this.soThe = "";
        this.ghiChu = "";
        this.ngayBatDau = "1985-01-01";
        this.ngayKetThuc = "1999-01-01";
    }

    public TheThuVien(String soThe, String ngayBatDau, String ngayKetThuc,String ghiChu) {
        this.soThe = soThe;
        this.ghiChu = ghiChu;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

}
