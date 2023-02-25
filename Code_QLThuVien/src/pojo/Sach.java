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
public class Sach {

    private String maS, tenS, theLoai, tacGia, nhaXB, hinh;
    private int namXB, soLuong, soLuotMuon;

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaS() {
        return maS;
    }

    public void setMaS(String maS) {
        this.maS = maS;
    }

    public String getTenS() {
        return tenS;
    }

    public void setTenS(String tenS) {
        this.tenS = tenS;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }

    public int getSoLuotMuon() {
        return soLuotMuon;
    }

    public void setSoLuotMuon(int soLuotMuon) {
        this.soLuotMuon = soLuotMuon;
    }

    public Sach() {
        this.maS = "";
        this.tenS = "";
        this.theLoai = "";
        this.tacGia = "";
        this.nhaXB = "";
        this.hinh = "";
        this.namXB = 1975;
        this.soLuong = 0;
        this.soLuotMuon = 0;
    }

    public Sach(String maS, String tenS, String theLoai, String tacGia, String nhaXB, int namXB, int soLuong) {
        this.maS = maS;
        this.tenS = tenS;
        this.theLoai = theLoai;
        this.tacGia = tacGia;
        this.nhaXB = nhaXB;
        this.namXB = namXB;
        this.soLuong = soLuong;
    }

    public Sach(String maS, String tenS, String theLoai, String tacGia, String nhaXB, String hinh, int namXB, int soLuong) {
        this.maS = maS;
        this.tenS = tenS;
        this.theLoai = theLoai;
        this.tacGia = tacGia;
        this.nhaXB = nhaXB;
        this.hinh = hinh;
        this.namXB = namXB;
        this.soLuong = soLuong;
    }

    public Sach(String maS, String tenS, String theLoai, String tacGia, String nhaXB, String hinh, int namXB, int soLuong, int soLuotMuon) {
        this.maS = maS;
        this.tenS = tenS;
        this.theLoai = theLoai;
        this.tacGia = tacGia;
        this.nhaXB = nhaXB;
        this.hinh = hinh;
        this.namXB = namXB;
        this.soLuong = soLuong;
        this.soLuotMuon = soLuotMuon;
    }

}
