/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author ADMIN
 */
public class NhaXuatBan {
    private String maNXB,tenNXB,diaChi,email,thongTinNguoiDaiDien;
    private int soLuotMuon;
    public String getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getThongTinNguoiDaiDien() {
        return thongTinNguoiDaiDien;
    }

    public void setThongTinNguoiDaiDien(String thongTinNguoiDaiDien) {
        this.thongTinNguoiDaiDien = thongTinNguoiDaiDien;
    }

    public int getSoLuotMuon() {
        return soLuotMuon;
    }

    public void setSoLuotMuon(int soLuotMuon) {
        this.soLuotMuon = soLuotMuon;
    }

    public NhaXuatBan() {
        this.maNXB = "";
        this.tenNXB = "";
        this.diaChi = "";
        this.email = "";
        this.thongTinNguoiDaiDien = "";
    }

    public NhaXuatBan(String maNXB, String tenNXB, String diaChi, String email, String thongTinNguoiDaiDien) {
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
        this.diaChi = diaChi;
        this.email = email;
        this.thongTinNguoiDaiDien = thongTinNguoiDaiDien;
    }

    public NhaXuatBan(String tenNXB, String diaChi, String email, String thongTinNguoiDaiDien) {
        this.tenNXB = tenNXB;
        this.diaChi = diaChi;
        this.email = email;
        this.thongTinNguoiDaiDien = thongTinNguoiDaiDien;
    }

    
    
    
}
