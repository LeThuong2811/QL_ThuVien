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
public class MuonTra {
     private String maMuonTra, maThe,maNV,tenNV,ngayMuon,ngayTra,maSach,ghiChu,daTra;
     private int soLuong;

    public String getDaTra() {
        return daTra;
    }

    public void setDaTra(String daTra) {
        this.daTra = daTra;
    }

     
    public String getMaMuonTra() {
        return maMuonTra;
    }

    public void setMaMuonTra(String maMuonTra) {
        this.maMuonTra = maMuonTra;
    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }
    
    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public MuonTra() {
        this.maMuonTra = "MT";
        this.maThe = "";
        this.maNV = "";
        this.tenNV = "";
        this.ngayMuon = "";
        this.ngayTra = "";
        this.ghiChu = "";
        this.daTra = "";
    }

    public MuonTra(String maMuonTra, String maThe, String maNV,String tenNV, String ngayMuon, String ngayTra, String ghiChu,String daTra) {
        this.maMuonTra = maMuonTra;
        this.maThe = maThe;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.ghiChu = ghiChu;
        this.daTra = daTra;
    }
    public MuonTra(String maMuonTra, String maThe, String maNV, String ngayMuon) {
        this.maMuonTra = maMuonTra;
        this.maThe = maThe;
        this.maNV = maNV;
        this.ngayMuon = ngayMuon;
    }
    
    
     
    
}
