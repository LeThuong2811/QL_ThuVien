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
public class TaiKhoan {

    private String username, password, loaiTK, tenLoaiTK, maNV, tenNV, sdt, gioiTinh, ngaySinh, hinh, cmnd, email, tinhTrang;
    private int luong;

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getTenLoaiTK() {
        return tenLoaiTK;
    }

    public void setTenLoaiTK(String tenLoaiTK) {
        this.tenLoaiTK = tenLoaiTK;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoaiTK() {
        return loaiTK;
    }

    public void setLoaiTK(String loaiTK) {
        this.loaiTK = loaiTK;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public TaiKhoan() {
        this.username = "";
        this.password = "";
        this.maNV = "";
        this.tenNV = "";
        this.sdt = "";
        this.gioiTinh = "Nam";
        this.ngaySinh = "1975-01-01";
        this.hinh = "src/HinhNhanVien/default.jpg";
        this.cmnd = "";
        this.email = "";
        this.tinhTrang = "";
        this.luong = 3000000;
    }

    public TaiKhoan(String username, String password, String loaiTK, String tenLoaiTK, String maNV, String tenNV, String sdt, String gioiTinh, String ngaySinh) {
        this.username = username;
        this.password = password;
        this.loaiTK = loaiTK;
        this.tenLoaiTK = tenLoaiTK;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
    }

    public TaiKhoan(String maNV, String tenNV, String sdt, String gioiTinh, String ngaySinh, String hinh, String cmnd, String email, int luong, String status) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.hinh = hinh;
        this.cmnd = cmnd;
        this.email = email;
        this.luong = luong;
        this.tinhTrang = status;
    }

    public TaiKhoan(String username, String password, String loaiTK, String maNV) {
        this.username = username;
        this.password = password;
        this.loaiTK = loaiTK;
        this.maNV = maNV;
    }

    public TaiKhoan(String username, String password, String loaiTK, String tenLoaiTK, String maNV, String tenNV, String sdt, String gioiTinh, String ngaySinh, String hinh, String cmnd, String email, String tinhTrang, int luong) {
        this.username = username;
        this.password = password;
        this.loaiTK = loaiTK;
        this.tenLoaiTK = tenLoaiTK;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.hinh = hinh;
        this.cmnd = cmnd;
        this.email = email;
        this.tinhTrang = tinhTrang;
        this.luong = luong;
    }
    
    
}
