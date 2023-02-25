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
public class DocGia {

    private String maDG, tenDG, diaChi, gioiTinh, email, sdt, soThe, ngaySinh, ngayBatDau, ngayHetHan, maSach;
    private int soLuotMuon;

    public String getMaDG() {
        return maDG;
    }

    public void setMaDG(String maDG) {
        this.maDG = maDG;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getSoThe() {
        return soThe;
    }

    public void setSoThe(String soThe) {
        this.soThe = soThe;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public int getSoLuotMuon() {
        return soLuotMuon;
    }

    public void setSoLuotMuon(int soLuotMuon) {
        this.soLuotMuon = soLuotMuon;
    }

    public String getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(String ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }
    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public DocGia() {
        this.maDG = "";
        this.tenDG = "";
        this.diaChi = "";
        this.gioiTinh = "";
        this.email = "";
        this.sdt = "";
        this.soThe = "";
        this.ngaySinh = "";
        this.ngayBatDau = "";
        this.ngayHetHan = "";
    }

    public DocGia(String maDG, String tenDG, String diaChi, String gioiTinh, String email, String sdt, String soThe, String ngaySinh, String ngayBatDau) {
        this.maDG = maDG;
        this.tenDG = tenDG;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.sdt = sdt;
        this.soThe = soThe;
        this.ngaySinh = ngaySinh;
        this.ngayBatDau = ngayBatDau;
    }

    public DocGia(String maDG, String tenDG, String diaChi, String gioiTinh, String email, String sdt, String soThe, String ngaySinh, String ngayBatDau, String ngayHetHan) {
        this.maDG = maDG;
        this.tenDG = tenDG;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.sdt = sdt;
        this.soThe = soThe;
        this.ngaySinh = ngaySinh;
        this.ngayBatDau = ngayBatDau;
        this.ngayHetHan = ngayHetHan;
    }

    public DocGia(String maDG, String tenDG, String diaChi, String gioiTinh, String email, String sdt, String soThe, String ngaySinh) {
        this.maDG = maDG;
        this.tenDG = tenDG;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.sdt = sdt;
        this.soThe = soThe;
        this.ngaySinh = ngaySinh;
    }

}
